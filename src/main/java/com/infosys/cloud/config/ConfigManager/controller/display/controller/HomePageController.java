/**
 * 
 */
package com.infosys.cloud.config.configmanager.controller.display.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.cloud.config.configmanager.model.service.AppDetailsServicePojo;
import com.infosys.cloud.config.configmanager.service.AppDetailsService;
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

	@RequestMapping(value = "/appdetails")
	public String index(ModelMap model) {
		List<AppDetailsServicePojo> listofApp = appDetailsService.fetchAllAppDetails();
		model.addAttribute("listOfApps", homePageService.convertAppDetailsForDropDown(listofApp));
		return "appselector";
	}
}
