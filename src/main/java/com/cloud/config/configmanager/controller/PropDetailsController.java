/**
 * 
 */
package com.cloud.config.configmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.config.configmanager.model.service.PropDetailsServicePojo;
import com.cloud.config.configmanager.service.PropDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author namadipta
 *
 */
@RestController
public class PropDetailsController {

	@Autowired
	private PropDetailsService propDetailsService;

	/**
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@PostMapping("/fetch/prop")
	public PropDetailsServicePojo fetchPropDetails(@RequestBody PropDetailsServicePojo request)
			throws JsonMappingException, JsonProcessingException {
		return propDetailsService.fetchLatestPropDetails(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@PostMapping("/prop/details")
	public String savePropDetails(@RequestBody PropDetailsServicePojo request) throws JsonProcessingException {
		return propDetailsService.savePropDetails(request);
	}

	/**
	 * @param modulename
	 * @param profile
	 * @param label
	 * @return
	 */
	@GetMapping("{modulename}/{profile}/{label}")
	public Environment fetchProps(@PathVariable String modulename, @PathVariable String profile,
			@PathVariable String label) {
		Environment environment = propDetailsService.fetchPropertiesforApps(modulename, profile, label);
		return environment;
	}

}
