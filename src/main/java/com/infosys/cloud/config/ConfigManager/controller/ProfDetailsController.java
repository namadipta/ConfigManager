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

import com.infosys.cloud.config.configmanager.model.service.ProfDetailsServicePojo;
import com.infosys.cloud.config.configmanager.service.ProfDetailsService;

/**
 * @author namadipta
 *
 */
@RestController
public class ProfDetailsController {

	@Autowired
	private ProfDetailsService profDetailsService;

	/**
	 * @param appId
	 * @return
	 */
	@GetMapping("/prof/details")
	public List<ProfDetailsServicePojo> fetchAllModuleForAppID(@RequestParam("appId") Long appId) {
		return profDetailsService.fetchAllProfForAppID(appId);
	}

	/**
	 * @param request
	 * @return
	 */
	@PostMapping("/prof/details")
	public String saveModule(@RequestBody ProfDetailsServicePojo request) {
		return profDetailsService.saveProf(request);
	}
}
