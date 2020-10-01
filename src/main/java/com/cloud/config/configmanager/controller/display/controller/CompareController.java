/**
 * 
 */
package com.cloud.config.configmanager.controller.display.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.config.configmanager.model.common.DropdownValue;
import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.display.AppsDisplayRequest;
import com.cloud.config.configmanager.model.display.ComparePropPojo;
import com.cloud.config.configmanager.model.service.CompareService;
import com.cloud.config.configmanager.model.service.UtilityService;
import com.cloud.config.configmanager.service.PropDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author namadipta
 *
 */
@Controller
public class CompareController {

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private PropDetailsService propDetailsService;

	@Autowired
	private CompareService compareService;

	/**
	 * @param model
	 * @param selectedAppRequest
	 * @return
	 */
	@PostMapping(value = "/loadcomparepage")
	public String loadCompareDetails(ModelMap model, @ModelAttribute("appId") AppsDisplayRequest selectedAppRequest) {
		String selectedAppId = selectedAppRequest.getAppId();

		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache(selectedAppId));
		model.addAttribute("appId", selectedAppId);
		model.addAttribute("comparepage", "true");
		model.addAttribute("compareprops", new ComparePropPojo());
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());

		model.addAttribute("content", "comparePropDetails");
		return "index";
	}

	@PostMapping(value = "/compareprops")
	public String compareProperties(ModelMap model, @ModelAttribute ComparePropPojo compareRequest)
			throws JsonMappingException, JsonProcessingException {
		String selectedAppId = compareRequest.getAppId();
		AppDisplayDetails appDisplayDetails = utilityService.getAppDetailsFromCache(selectedAppId);
		appDisplayDetails.setListOfSourcePropVersion(propDetailsService.fetchAllPropVersion(compareRequest.getAppId(),
				compareRequest.getSourceSelectedModule(), compareRequest.getSourceSelectedProfile(),
				compareRequest.getSourceSelectedLabel()));
		appDisplayDetails.setListOfTargetPropVersion(propDetailsService.fetchAllPropVersion(compareRequest.getAppId(),
				compareRequest.getTargetSelectedModule(), compareRequest.getTargetSelectedProfile(),
				compareRequest.getTargetSelectedLabel()));

		model.addAttribute("appDisplayDetails", appDisplayDetails);
		model.addAttribute("appId", selectedAppId);
		model.addAttribute("comparepage", "true");
		model.addAttribute("compareprops", compareRequest);
		model.addAttribute("compareResponse", compareService.compareProps(compareRequest));
		model.addAttribute("content", "comparePropDetails");
		return "index";
	}

	/**
	 * @param request
	 * @param query
	 * @return
	 */
	@GetMapping(value = "/fetchpropversion")
	public @ResponseBody List<DropdownValue> fetchpropversion(@RequestParam String appId,
			@RequestParam String selectedModule, @RequestParam String selectedProfile,
			@RequestParam String selectedLabel) {
		return propDetailsService.fetchAllPropVersion(appId, selectedModule, selectedProfile, selectedLabel);
	}
}
