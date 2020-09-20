/**
 * 
 */
package com.infosys.cloud.config.configmanager.controller.display.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.infosys.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.infosys.cloud.config.configmanager.model.display.AppsDisplayRequest;
import com.infosys.cloud.config.configmanager.model.service.AppDetailsServicePojo;
import com.infosys.cloud.config.configmanager.model.service.EnvDetailsServicePojo;
import com.infosys.cloud.config.configmanager.model.service.ModuleDetailsServicePojo;
import com.infosys.cloud.config.configmanager.model.service.ProfDetailsServicePojo;
import com.infosys.cloud.config.configmanager.service.AppDetailsService;
import com.infosys.cloud.config.configmanager.service.EnvDetailsService;
import com.infosys.cloud.config.configmanager.service.ModuleDetailsService;
import com.infosys.cloud.config.configmanager.service.ProfDetailsService;
import com.infosys.cloud.config.configmanager.service.display.service.HomePageService;

/**
 * @author namadipta
 *
 */
@Controller
public class HomePageController {

	@Autowired
	private AppDetailsService appDetailsService;

	@Autowired
	private HomePageService homePageService;

	@Autowired
	private EnvDetailsService envDetailsService;

	@Autowired
	private ModuleDetailsService moduleDetailsService;

	@Autowired
	private ProfDetailsService profDetailsService;

	@GetMapping(value = "/apps")
	public String index(ModelMap model) {
		List<AppDetailsServicePojo> listofApp = appDetailsService.fetchAllAppDetails();
		AppsDisplayRequest modelData = new AppsDisplayRequest();
		modelData.setListOfApps(homePageService.convertAppDetailsForDropDown(listofApp));
		model.addAttribute("appData", modelData);
		return "appselector";
	}

	@PostMapping(value = "/appdetails")
	public String loadAppDetails(ModelMap model, @ModelAttribute("appName") AppsDisplayRequest selectedAppRequest) {
		String selectedAppId = selectedAppRequest.getAppName();
		AppDisplayDetails appDisplayDetails = new AppDisplayDetails();
		if (StringUtils.isNotBlank(selectedAppId)) {
			List<EnvDetailsServicePojo> envDetails = envDetailsService
					.fetchAllEnvForAppID(Long.parseLong(selectedAppId));
			appDisplayDetails.setListOfEnvs(homePageService.convertEnvDetailsForDropDown(envDetails));
		}

		if (StringUtils.isNotBlank(selectedAppId)) {
			List<ModuleDetailsServicePojo> moduleDetails = moduleDetailsService
					.fetchAllModuleForAppID(Long.parseLong(selectedAppId));
			appDisplayDetails.setListOfModules(homePageService.convertModuleDetailsForDropDown(moduleDetails));
		}

		if (StringUtils.isNotBlank(selectedAppId)) {
			List<ProfDetailsServicePojo> profDetails = profDetailsService
					.fetchAllProfForAppID(Long.parseLong(selectedAppId));
			appDisplayDetails.setListOfProfs(homePageService.convertProfDetailsForDropDown(profDetails));
		}
		model.addAttribute("appDisplayDetails", appDisplayDetails);
		model.addAttribute("content", "appDetails");
		return "index";
	}
}
