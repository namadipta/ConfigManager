/**
 * 
 */
package com.cloud.config.configmanager.controller.display.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloud.config.configmanager.model.display.AllUserAccessDetailsPojo;
import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.service.UtilityService;
import com.cloud.config.configmanager.service.UserAccessService;

/**
 * @author namadipta
 *
 */
@Controller
public class AdminController {

	@Autowired
	private UserAccessService userAccessService;

	@Autowired
	private UtilityService utilityService;

	/**
	 * @return
	 */
	@PostMapping("/loaduseraccess")
	public String loadUserAccessDetails(ModelMap model) {

		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		AllUserAccessDetailsPojo allUserAccessDetailsPojo = new AllUserAccessDetailsPojo();
		allUserAccessDetailsPojo.setListOfUser(userAccessService.findAll());
		model.addAttribute("allUserAccess", allUserAccessDetailsPojo);
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());
		model.addAttribute("content", "allUserAccess");
		return "configHome.html";
	}

	/**
	 * @param model
	 * @param properties
	 * @return
	 */
	@PostMapping(value = "/saveuser")
	public String saveUser(ModelMap model, @ModelAttribute AllUserAccessDetailsPojo userDetails) {
		userAccessService.saveUserDetails(userDetails);
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache());
		AllUserAccessDetailsPojo allUserAccessDetailsPojo = new AllUserAccessDetailsPojo();
		allUserAccessDetailsPojo.setListOfUser(userAccessService.findAll());
		model.addAttribute("allUserAccess", allUserAccessDetailsPojo);
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());
		model.addAttribute("content", "allUserAccess");
		return "configHome.html";
	}
}
