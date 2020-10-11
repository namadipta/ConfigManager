/**
 * 
 */
package com.cloud.config.configmanager.controller.display.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.service.PropDetailsServicePojo;
import com.cloud.config.configmanager.model.service.PropDetailsServiceResponse;
import com.cloud.config.configmanager.model.service.PropertyPojo;
import com.cloud.config.configmanager.model.service.SavePropDetailRequest;
import com.cloud.config.configmanager.model.service.UtilityService;
import com.cloud.config.configmanager.service.PropDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author namadipta
 *
 */
@Controller
public class HomePageController {

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private PropDetailsService propDetailsService;

//	/**
//	 * @param model
//	 * @return
//	 */
//	@GetMapping(value = "/apps")
//	public String index(ModelMap model) {
//		List<AppDetailsServicePojo> listofApp = appDetailsService.fetchAllAppDetails();
//		AppsDisplayRequest modelData = new AppsDisplayRequest();
//		modelData.setListOfApps(homePageService.convertAppDetailsForDropDown(listofApp));
//		model.addAttribute("appData", modelData);
//		return "appselector";
//	}

	/**
	 * @param model
	 * @param selectedAppRequest
	 * @return
	 */
	@GetMapping(value = "/appdetails")
	public String loadAppDetails(ModelMap model) {

		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());

		model.addAttribute("content", "propdetails");
		model.addAttribute("propDetails", null);
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());
		model.addAttribute("appdetails", "true");
		return "configHome";
	}

	/**
	 * @param model
	 * @param selectedAppRequest
	 * @return
	 */
	@PostMapping(value = "/loadmodule")
	public String loadmodule(ModelMap model, @ModelAttribute AppDisplayDetails selectedAppRequest) {

		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		model.addAttribute("content", "propdetails");
		model.addAttribute("propDetails", null);
		if (Objects.nonNull(selectedAppRequest) && StringUtils.isNotBlank(selectedAppRequest.getModuleId())) {
			selectedAppRequest.setSelectedModule(selectedAppRequest.getModuleId());
		}
		model.addAttribute("selectedAppRequest", selectedAppRequest);
		model.addAttribute("appdetails", "true");
		return "configHome";
	}

	/**
	 * TODO : Module mandatory check
	 * 
	 * @param model
	 * @param selectedAppRequest
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@PostMapping(value = "/propdetails")
	public String loadPropDetails(ModelMap model,
			@ModelAttribute("appDisplayDetails") AppDisplayDetails selectedAppRequest)
			throws JsonMappingException, JsonProcessingException {
		PropDetailsServicePojo request = new PropDetailsServicePojo();
		request.setProfId(Long.parseLong(selectedAppRequest.getSelectedProfile()));
		request.setLabelId(Long.parseLong(selectedAppRequest.getSelectedLabel()));
		request.setModId(Long.parseLong(selectedAppRequest.getSelectedModule()));

		PropDetailsServicePojo selectedPropDetails = propDetailsService.fetchLatestPropDetails(request);

		PropDetailsServiceResponse propDetailsResponse = propDetailsService
				.prepareResponseForRender(selectedPropDetails);
		model.addAttribute("propDetails", propDetailsResponse);
		SavePropDetailRequest savePropDetailRequest = new SavePropDetailRequest();
		List<PropertyPojo> propList = propDetailsService.processProperties(selectedPropDetails);
		if (CollectionUtils.isEmpty(propList)) {
			propList = Arrays.asList(new PropertyPojo());
			savePropDetailRequest.setSavePropDetails(propList);
		} else {
			savePropDetailRequest.setSavePropDetails(propList);
		}

		model.addAttribute("saveProperties", savePropDetailRequest);
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		model.addAttribute("selectedAppRequest", selectedAppRequest);
		model.addAttribute("content", "propdetails");
		model.addAttribute("appdetails", "true");
		return "configHome";
	}

	/**
	 * @param model
	 * @param savePropDetailsRequest
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@PostMapping(value = "/savepropdetails")
	public String savePropDetails(ModelMap model, @ModelAttribute SavePropDetailRequest properties)
			throws JsonMappingException, JsonProcessingException {
		String latestPropId = propDetailsService.savePropDetails(properties);
		PropDetailsServicePojo savePropDetails = propDetailsService.fetchLatestPropDetails(latestPropId);

		PropDetailsServiceResponse propDetailsResponse = propDetailsService.prepareResponseForRender(savePropDetails);

		model.addAttribute("propDetails", propDetailsResponse);
		SavePropDetailRequest savePropDetailRequest = new SavePropDetailRequest();
		savePropDetailRequest.setSavePropDetails(propDetailsService.processProperties(savePropDetails));
		model.addAttribute("saveProperties", savePropDetailRequest);
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		AppDisplayDetails selectedAppRequest = propDetailsService.prepareSelectedAppRequest(properties);
		model.addAttribute("selectedAppRequest", selectedAppRequest);
		model.addAttribute("content", "propdetails");
		model.addAttribute("appdetails", "true");
		return "configHome";
	}
}
