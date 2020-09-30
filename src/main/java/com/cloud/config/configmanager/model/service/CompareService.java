/**
 * 
 */
package com.cloud.config.configmanager.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.config.configmanager.model.common.DropdownValue;
import com.cloud.config.configmanager.model.display.ChangedData;
import com.cloud.config.configmanager.model.display.CompareDetailsResponse;
import com.cloud.config.configmanager.model.display.ComparePropPojo;
import com.cloud.config.configmanager.model.entity.PropDetailsEntity;
import com.cloud.config.configmanager.service.PropDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author namadipta
 *
 */
@Service
public class CompareService {

	@Autowired
	private PropDetailsService propDetailsService;

	private static ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * @param compareRequest
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	public CompareDetailsResponse compareProps(ComparePropPojo compareRequest)
			throws JsonMappingException, JsonProcessingException {
		CompareDetailsResponse response = new CompareDetailsResponse();
		PropDetailsEntity sourceProps = propDetailsService.fetchPropDetails(compareRequest.getAppId(),
				compareRequest.getSourceSelectedModule(), compareRequest.getSourceSelectedProfile(),
				compareRequest.getSourceSelectedLabel(), compareRequest.getSourceSelectedPropVersion());

		PropDetailsEntity targetProps = propDetailsService.fetchPropDetails(compareRequest.getAppId(),
				compareRequest.getTargetSelectedModule(), compareRequest.getTargetSelectedProfile(),
				compareRequest.getTargetSelectedLabel(), compareRequest.getTargetSelectedPropVersion());

		if (Objects.nonNull(sourceProps) && StringUtils.isNotBlank(sourceProps.getPropData())) {
			final Map<String, PropDataPojo> sourcePropsData = (Map<String, PropDataPojo>) MAPPER
					.readValue(sourceProps.getPropData(), new TypeReference<Map<String, PropDataPojo>>() {
					});

			if (Objects.nonNull(targetProps) && StringUtils.isNotBlank(targetProps.getPropData())) {
				final Map<String, PropDataPojo> targetPropsData = (Map<String, PropDataPojo>) MAPPER
						.readValue(targetProps.getPropData(), new TypeReference<Map<String, PropDataPojo>>() {
						});

				List<ChangedData> changedValues = new ArrayList<>();
				List<DropdownValue> newProps = new ArrayList<>();
				List<DropdownValue> removedProps = new ArrayList<>();
				targetPropsData.entrySet().parallelStream().forEach(targetProp -> {
					if (!sourcePropsData.containsKey(targetProp.getKey())) {
						DropdownValue data = new DropdownValue();
						data.setId(targetProp.getKey());
						data.setText(targetProp.getValue().getValue());
						newProps.add(data);
					}
					PropDataPojo sourceData = sourcePropsData.get(targetProp.getKey());
					if (Objects.nonNull(sourceData)
							&& !StringUtils.equalsIgnoreCase(sourceData.getValue(), targetProp.getValue().getValue())) {
						ChangedData changedData = new ChangedData();
						changedData.setId(targetProp.getKey());
						changedData.setOldValue(sourceData.getValue());
						changedData.setNewValue(targetProp.getValue().getValue());
						changedValues.add(changedData);
					}

				});

				sourcePropsData.entrySet().parallelStream().forEach(sourceProp -> {
					if (!targetPropsData.containsKey(sourceProp.getKey())) {
						DropdownValue data = new DropdownValue();
						data.setId(sourceProp.getKey());
						data.setText(sourceProp.getValue().getValue());
						removedProps.add(data);
					}
				});
				response.setRemovedProps(removedProps);
				response.setNewProps(newProps);
				response.setChangedValues(changedValues);
			}
		}
		return response;
	}
}
