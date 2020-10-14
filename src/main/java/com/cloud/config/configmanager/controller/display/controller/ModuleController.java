/**
 * 
 */
package com.cloud.config.configmanager.controller.display.controller;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloud.config.configmanager.model.display.AddConfigDetails;
import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.service.UtilityService;
import com.cloud.config.configmanager.service.LabelDetailsService;
import com.cloud.config.configmanager.service.ModuleDetailsService;
import com.cloud.config.configmanager.service.ProfDetailsService;

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

	@Autowired
	private ProfDetailsService profDetailsService;

	@Autowired
	private LabelDetailsService labelDetailsService;

	/**
	 * @param model
	 * @return
	 */
	/**
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/loadaddconfig")
	public String loadaddmodule(ModelMap model) {

		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		model.addAttribute("addconfig", new AddConfigDetails());
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());
		model.addAttribute("content", "addConfig");
		return "configHome.html";
	}

	/**
	 * @param model
	 * @param addModule
	 * @return
	 */
	@PostMapping(value = "/addconfig")
	public String addmodule(ModelMap model, @Autowired AddConfigDetails request) {
		if (Objects.nonNull(request)) {
			if (StringUtils.equalsIgnoreCase(request.getConfigType(), "module")) {
				moduleDetailsService.saveModule(request);
			} else if (StringUtils.equalsIgnoreCase(request.getConfigType(), "label")) {
				labelDetailsService.saveEnv(request);
			} else if (StringUtils.equalsIgnoreCase(request.getConfigType(), "profile")) {
				profDetailsService.saveProf(request);
			}
		}

		model.addAttribute("addconfig", new AddConfigDetails());
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		model.addAttribute("content", "addConfig");
		return "configHome.html";
	}
}
