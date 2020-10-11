/**
 * 
 */
package com.cloud.config.configmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.config.configmanager.model.service.ModuleDetailsServicePojo;
import com.cloud.config.configmanager.service.ModuleDetailsService;

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
	public List<ModuleDetailsServicePojo> fetchAllModuleForAppID() {
		return moduleDetailsService.fetchAllModuleForAppID();
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
