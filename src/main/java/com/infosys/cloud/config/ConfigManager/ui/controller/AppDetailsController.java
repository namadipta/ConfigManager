/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.cloud.config.ConfigManager.ui.model.service.AppDetailsServicePojo;
import com.infosys.cloud.config.ConfigManager.ui.service.AppDetailsService;

/**
 * @author namadipta
 *
 */
@RestController
public class AppDetailsController {

	@Autowired
	private AppDetailsService appDetailsService;

	@GetMapping("/app/details")
	public List<AppDetailsServicePojo> fetchAllAppDetails() {
		return appDetailsService.fetchAllAppDetails();
	}

	@PostMapping("/app/details")
	public String addNewApp(@RequestBody AppDetailsServicePojo request) {
		return appDetailsService.addNewApp(request);
	}
}
