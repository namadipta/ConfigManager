/**
 * 
 */
package com.infosys.cloud.config.configmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.cloud.config.configmanager.model.service.EnvDetailsServicePojo;
import com.infosys.cloud.config.configmanager.service.EnvDetailsService;

/**
 * @author namadipta
 *
 */
@RestController
public class EnvDetailsController {

	@Autowired
	private EnvDetailsService envDetailsService;

	/**
	 * @param appId
	 * @return
	 */
	@GetMapping("/env/details")
	public List<EnvDetailsServicePojo> fetchAllModuleForAppID(@RequestParam("appId") Long appId) {
		return envDetailsService.fetchAllEnvForAppID(appId);
	}

	/**
	 * @param request
	 * @return
	 */
	@PostMapping("/env/details")
	public String saveModule(@RequestBody EnvDetailsServicePojo request) {
		return envDetailsService.saveEnv(request);
	}
}
