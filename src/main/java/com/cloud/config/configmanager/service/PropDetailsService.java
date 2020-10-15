/**
 * 
 */
package com.cloud.config.configmanager.service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.MapUtils;

import com.cloud.config.configmanager.mapper.PropDetailsMapper;
import com.cloud.config.configmanager.model.common.DropdownValue;
import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.entity.PropDetailsEntity;
import com.cloud.config.configmanager.model.service.PropDataPojo;
import com.cloud.config.configmanager.model.service.PropDetailsServicePojo;
import com.cloud.config.configmanager.model.service.PropDetailsServiceResponse;
import com.cloud.config.configmanager.model.service.PropertyPojo;
import com.cloud.config.configmanager.model.service.SavePropDetailRequest;
import com.cloud.config.configmanager.model.service.UtilityService;
import com.cloud.config.configmanager.repo.PropDetailsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

/**
 * @author namadipta
 *
 */
@Service
public class PropDetailsService {

	@Autowired
	private PropDetailsRepo propDetailsRepo;

	@Autowired
	private PropDetailsMapper propDetailsMapper;

	private static ObjectMapper MAPPER = new ObjectMapper();

	private InetAddress serverAddress;

	@Autowired
	private UtilityService utilityService;

	/**
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	public PropDetailsServicePojo fetchLatestPropDetails(final PropDetailsServicePojo request)
			throws JsonMappingException, JsonProcessingException {

		PropDetailsEntity latestPropDetails = null;

		if (Objects.nonNull(request) && Objects.nonNull(request.getPropVersion())) {
			latestPropDetails = findFirstByModIdAndLabelIdAndProfIdAndPropVersion(request);
		} else {
			latestPropDetails = findFirstByModIdAndLabelIdAndProfIdOrderByPropVersionDesc(request);
		}
		Map<String, PropDataPojo> propData = new HashMap<>();
		if (Objects.nonNull(latestPropDetails) && StringUtils.isNotBlank(latestPropDetails.getPropData())) {
			propData = (Map<String, PropDataPojo>) MAPPER.readValue(latestPropDetails.getPropData(),
					new TypeReference<Map<String, PropDataPojo>>() {
					});
		}
		PropDetailsServicePojo response = propDetailsMapper.map(latestPropDetails);
		if (!MapUtils.isEmpty(propData)) {
			response.setPropData(propData);
		}
		return response;
	}

	/**
	 * @param request
	 * @return
	 */
	public PropDetailsEntity findFirstByModIdAndLabelIdAndProfIdOrderByPropVersionDesc(
			final PropDetailsServicePojo request) {
		PropDetailsEntity latestPropDetails = propDetailsRepo.findFirstByModIdAndLabelIdAndProfIdOrderByPropVersionDesc(
				request.getModId(), request.getLabelId(), request.getProfId());
		return latestPropDetails;
	}

	/**
	 * @param request
	 * @return
	 */
	public PropDetailsEntity findFirstByModIdAndLabelIdAndProfIdAndPropVersion(final PropDetailsServicePojo request) {
		PropDetailsEntity latestPropDetails = propDetailsRepo.findFirstByModIdAndLabelIdAndProfIdAndPropVersion(
				request.getModId(), request.getLabelId(), request.getProfId(), request.getPropVersion());
		return latestPropDetails;
	}

	/**
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	public String savePropDetails(final PropDetailsServicePojo request) throws JsonProcessingException {

		PropDetailsEntity latestPropDetails = propDetailsRepo.findFirstByModIdAndLabelIdAndProfIdOrderByPropVersionDesc(
				request.getModId(), request.getLabelId(), request.getProfId());
		PropDetailsEntity saveRequest = propDetailsMapper.prepareSaveRequest(request);
		if (!MapUtils.isEmpty(request.getPropData())) {
			String propData = MAPPER.writeValueAsString(request.getPropData());
			saveRequest.setPropData(propData);
		}
		PropDetailsEntity savedData = saveProperties(saveRequest, latestPropDetails.getPropVersion());
		return savedData.getPropId().toString();

	}

	/**
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	PropDetailsEntity saveProperties(PropDetailsEntity saveRequest, Long proVersion) throws JsonProcessingException {
		if (Objects.nonNull(proVersion)) {
			saveRequest.setPropVersion(proVersion + 1);
		} else {
			saveRequest.setPropVersion(Long.parseLong("1"));
		}

		PropDetailsEntity savedData = propDetailsRepo.save(saveRequest);
		return savedData;
	}

	/**
	 * @param selectedPropDetails
	 * @return
	 */
	public PropDetailsServiceResponse prepareResponseForRender(PropDetailsServicePojo selectedPropDetails) {
		PropDetailsServiceResponse propDetailsServiceResponse = new PropDetailsServiceResponse();
		if (Objects.nonNull(selectedPropDetails)) {
			propDetailsServiceResponse = propDetailsMapper.mapResponse(selectedPropDetails);
		}
		return propDetailsServiceResponse;

	}

	/**
	 * @param selectedPropDetails
	 */
	public List<PropertyPojo> processProperties(PropDetailsServicePojo selectedPropDetails) {
		if (Objects.nonNull(selectedPropDetails) && !MapUtils.isEmpty(selectedPropDetails.getPropData())) {
			List<PropertyPojo> propData = new LinkedList<>();
			selectedPropDetails.getPropData().entrySet().stream().forEach(eachProp -> {
				PropertyPojo dropdownValue = new PropertyPojo();
				dropdownValue.setId(eachProp.getKey());
				PropDataPojo value = eachProp.getValue();
				if (BooleanUtils.isNotTrue(value.getHide())) {
					dropdownValue.setText(value.getValue());
				} else {
					dropdownValue.setText("**********************");
					dropdownValue.setHide(Boolean.TRUE);
				}
				propData.add(dropdownValue);
			});
			return propData;
		}
		return null;
	}

	/**
	 * @param modulename
	 * @param profile
	 * @param label
	 * @return
	 */
	public Environment fetchPropertiesforApps(String modulename, String profile, String label) {
		Environment environment = null;
		try {
			PropDetailsServicePojo request = new PropDetailsServicePojo();
			serverAddress = InetAddress.getLocalHost();
			if (Objects.nonNull(serverAddress)) {
				serverAddress.getHostName();
				AppDisplayDetails displayDetails = utilityService.getAppDetailsFromCache();
				request.setModId(utilityService.findModuleIdBasedOnName(displayDetails, modulename));
				request.setProfId(utilityService.findProfileIdBasedOnName(displayDetails, profile));
				request.setLabelId(utilityService.findLabelIdBasedOnName(displayDetails, label));
				PropDetailsServicePojo dbResponse = fetchLatestPropDetails(request);
				environment = prepareEnvironmentResponse(dbResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return environment;
	}

	/**
	 * @param dbResponse
	 * @return
	 */
	private Environment prepareEnvironmentResponse(PropDetailsServicePojo dbResponse) {
		if (Objects.nonNull(dbResponse) && !MapUtils.isEmpty(dbResponse.getPropData())) {
			Environment environment = new Environment(dbResponse.getModId().toString(),
					dbResponse.getProfId().toString(), dbResponse.getLabelId().toString(),
					dbResponse.getPropVersion().toString(), "ACTIVE");
			List<PropertySource> propertySources = new ArrayList<>();

			dbResponse.getPropData().entrySet().stream().forEach(eachProp -> {
				PropertySource propertySource = new PropertySource(eachProp.getKey(),
						ImmutableMap.of(eachProp.getKey(), eachProp.getValue().getValue()));
				propertySources.add(propertySource);
			});
			environment.addAll(propertySources);

			return environment;
		}
		return null;
	}

	/**
	 * @param properties
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	public String savePropDetails(SavePropDetailRequest properties)
			throws JsonMappingException, JsonProcessingException {
		PropDetailsServicePojo latestPropDetails = this.fetchLatestPropDetails(propDetailsMapper.map(properties));
		if (Objects.nonNull(properties) && !CollectionUtils.isEmpty(properties.getSavePropDetails())
				&& Objects.nonNull(latestPropDetails) && !MapUtils.isEmpty(latestPropDetails.getPropData())) {
			properties.getSavePropDetails().stream().filter(
					j -> Objects.nonNull(j) && StringUtils.isNotBlank(j.getId()) && StringUtils.isNotBlank(j.getText()))
					.forEach(requestProp -> {
						PropDataPojo data = latestPropDetails.getPropData().get(requestProp.getId());
						if (Objects.nonNull(data) && BooleanUtils.isTrue(data.getHide())
								&& StringUtils.equalsIgnoreCase(requestProp.getText(), "**********************")) {
							requestProp.setText(data.getValue());
						}
					});
		}
		PropDetailsEntity request = propDetailsMapper.prepareSaveRequest(properties);

		Map<String, PropDataPojo> propData = new HashMap<>();

		if (!CollectionUtils.isEmpty(properties.getSavePropDetails())) {
			properties.getSavePropDetails().stream().filter(
					j -> Objects.nonNull(j) && StringUtils.isNotBlank(j.getId()) && StringUtils.isNotBlank(j.getText()))
					.forEach(i -> {
						PropDataPojo data = new PropDataPojo();
						data.setHide(i.getHide());
						data.setValue(i.getText());
						propData.put(i.getId(), data);
					});
		}
		String propDataString = MAPPER.writeValueAsString(propData);
		request.setPropData(propDataString);
		PropDetailsEntity savedData = this.saveProperties(request, latestPropDetails.getPropVersion());
		return savedData.getPropId().toString();
	}

	/**
	 * @param latestPropId
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	public PropDetailsServicePojo fetchLatestPropDetails(String latestPropId)
			throws JsonMappingException, JsonProcessingException {

		Optional<PropDetailsEntity> latestPropDetails = null;
		PropDetailsServicePojo response = null;
		if (StringUtils.isNotBlank(latestPropId)) {
			latestPropDetails = propDetailsRepo.findById(Long.parseLong(latestPropId));
		}
		if (latestPropDetails.isPresent()) {
			Map<String, PropDataPojo> propData = new HashMap<>();
			if (Objects.nonNull(latestPropDetails) && StringUtils.isNotBlank(latestPropDetails.get().getPropData())) {
				propData = (Map<String, PropDataPojo>) MAPPER.readValue(latestPropDetails.get().getPropData(),
						new TypeReference<Map<String, PropDataPojo>>() {
						});
			}
			response = propDetailsMapper.map(latestPropDetails.get());
			if (!MapUtils.isEmpty(propData)) {
				response.setPropData(propData);
			}
		}
		return response;
	}

	/**
	 * @param selectedAppRequest
	 * @return
	 */
	public PropDetailsServiceResponse fetchLatestPropVersion(AppDisplayDetails selectedAppRequest) {
		PropDetailsEntity latestPropDetails = findFirstByModIdAndLabelIdAndProfIdOrderByPropVersionDesc(
				propDetailsMapper.map(selectedAppRequest));
		PropDetailsServiceResponse response = propDetailsMapper.mapping(latestPropDetails);
		if (Objects.nonNull(response)) {
			response = new PropDetailsServiceResponse();
			response.setPropId(0l);
			response.setPropVersion(0l);
		}
		return response;
	}

	/**
	 * @param appId
	 * @param selectedModule
	 * @param selectedProfile
	 * @param selectedLabel
	 * @return
	 */
	public List<DropdownValue> fetchAllPropVersion(String selectedModule, String selectedProfile,
			String selectedLabel) {
		List<PropDetailsEntity> allPropVersion = propDetailsRepo
				.findAllByModIdAndLabelIdAndProfIdOrderByPropVersionDesc(Long.parseLong(selectedModule),
						Long.parseLong(selectedLabel), Long.parseLong(selectedProfile));
		if (!CollectionUtils.isEmpty(allPropVersion)) {
			Function<PropDetailsEntity, DropdownValue> convertEntityToDropdown = req -> propDetailsMapper
					.mapDropDown(req);
			List<DropdownValue> response = allPropVersion.stream().map(i -> convertEntityToDropdown.apply(i))
					.collect(Collectors.toList());
			return response;
		}
		return null;
	}

	/**
	 * @param appId
	 * @param targetSelectedModule
	 * @param targetSelectedProfile
	 * @param targetSelectedLabel
	 * @param targetSelectedPropVersion
	 * @return
	 */
	public PropDetailsEntity fetchPropDetails(String targetSelectedModule, String targetSelectedProfile,
			String targetSelectedLabel, String targetSelectedPropVersion) {
		return propDetailsRepo.findFirstByModIdAndLabelIdAndProfIdAndPropVersion(Long.parseLong(targetSelectedModule),
				Long.parseLong(targetSelectedLabel), Long.parseLong(targetSelectedProfile),
				Long.parseLong(targetSelectedPropVersion));
	}

	/**
	 * @param properties
	 * @return
	 */
	public AppDisplayDetails prepareSelectedAppRequest(SavePropDetailRequest properties) {
		return propDetailsMapper.mapper(properties);
	}
}
