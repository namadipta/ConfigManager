/**
 * 
 */
package com.cloud.config.configmanager.controller.display.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.display.ModuleDisplayDetails;
import com.cloud.config.configmanager.model.service.UtilityService;
import com.cloud.config.configmanager.service.ModuleDetailsService;

/**
 * @author namadipta
 *
 */
@Controller
public class ModuleController {

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private ModuleDetailsService moduleDetailsService;

	/**
	 * @param model
	 * @return
	 */
	/**
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/loadaddmodule")
	public String loadaddmodule(ModelMap model) {

		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		model.addAttribute("addmodule", new ModuleDisplayDetails());
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());
		model.addAttribute("content", "addModule");
		return "configHome.html";
	}

	/**
	 * @param model
	 * @param addModule
	 * @return
	 */
	@PostMapping(value = "/addmodule")
	public String addmodule(ModelMap model, @Autowired ModuleDisplayDetails addModule) {
		moduleDetailsService.saveModule(addModule);
		model.addAttribute("addmodule", new ModuleDisplayDetails());
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		model.addAttribute("content", "addModule");
		return "configHome.html";
	}
}
