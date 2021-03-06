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
	public String loadCompareDetails(ModelMap model) {

		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		model.addAttribute("comparepage", "true");
		model.addAttribute("compareprops", new ComparePropPojo());
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());

		model.addAttribute("content", "comparePropDetails");
		return "configHome.html";
	}

	@PostMapping(value = "/compareprops")
	public String compareProperties(ModelMap model, @ModelAttribute ComparePropPojo compareRequest,
			@ModelAttribute("appDisplayDetails") AppDisplayDetails selectedAppRequest)
			throws JsonMappingException, JsonProcessingException {
		AppDisplayDetails appDisplayDetails = utilityService.getAppDetailsFromCache();
		appDisplayDetails.setListOfSourcePropVersion(
				propDetailsService.fetchAllPropVersion(compareRequest.getSourceSelectedModule(),
						compareRequest.getSourceSelectedProfile(), compareRequest.getSourceSelectedLabel()));
		appDisplayDetails.setListOfTargetPropVersion(
				propDetailsService.fetchAllPropVersion(compareRequest.getTargetSelectedModule(),
						compareRequest.getTargetSelectedProfile(), compareRequest.getTargetSelectedLabel()));

		model.addAttribute("appDisplayDetails", appDisplayDetails);
		model.addAttribute("comparepage", "true");
		model.addAttribute("compareprops", compareRequest);
		model.addAttribute("compareResponse", compareService.compareProps(compareRequest));
		model.addAttribute("content", "comparePropDetails");
		model.addAttribute("selectedAppRequest", selectedAppRequest);

		return "configHome.html";
	}

	/**
	 * @param request
	 * @param query
	 * @return
	 */
	@GetMapping(value = "/fetchpropversion")
	public @ResponseBody List<DropdownValue> fetchpropversion(@RequestParam String selectedModule,
			@RequestParam String selectedProfile, @RequestParam String selectedLabel) {
		return propDetailsService.fetchAllPropVersion(selectedModule, selectedProfile, selectedLabel);
	}
}
