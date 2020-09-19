/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.cloud.config.ConfigManager.ui.model.service.ModuleDetailsServicePojo;
import com.infosys.cloud.config.ConfigManager.ui.service.ModuleDetailsService;

/**
 * @author namadipta
 *
 */
@RestController
public class ModuleDetailsController {

	@Autowired
	private ModuleDetailsService moduleDetailsService;

	/**
	 * @param appId
	 * @return
	 */
	@GetMapping("/module/details")
	public List<ModuleDetailsServicePojo> fetchAllModuleForAppID(@RequestParam("appId") Long appId) {
		return moduleDetailsService.fetchAllModuleForAppID(appId);
	}

	/**
	 * @param request
	 * @return
	 */
	@PostMapping("/module/details")
	public String saveModule(@RequestBody ModuleDetailsServicePojo request) {
		return moduleDetailsService.saveModule(request);
	}
}
