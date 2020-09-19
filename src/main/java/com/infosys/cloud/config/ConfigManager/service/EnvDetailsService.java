/**
 * 
 */
package com.infosys.cloud.config.configmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.cloud.config.configmanager.mapper.EnvDetailsMapper;
import com.infosys.cloud.config.configmanager.model.entity.EnvDetailsEntity;
import com.infosys.cloud.config.configmanager.model.service.EnvDetailsServicePojo;
import com.infosys.cloud.config.configmanager.repo.EnvDetailsRepo;

/**
 * @author namadipta
 *
 */
@Service
public class EnvDetailsService {

	@Autowired
	private EnvDetailsRepo envDetailsRepo;

	@Autowired
	private EnvDetailsMapper envDetailsMapper;

	/**
	 * fetch all Module for an given appId
	 * 
	 * @param request
	 * @return
	 */
	public List<EnvDetailsServicePojo> fetchAllEnvForAppID(final Long appId) {
		List<EnvDetailsEntity> envDetails = envDetailsRepo.findByAppId(appId);
		return envDetailsMapper.map(envDetails);
	}

	/**
	 * Save a Module for an AppId
	 * 
	 * @param request
	 * @return
	 */
	public String saveEnv(final EnvDetailsServicePojo request) {
		EnvDetailsEntity requestEntity = envDetailsMapper.saveMap(request);
		EnvDetailsEntity savedData = envDetailsRepo.save(requestEntity);
		return savedData.getEnvId().toString();
	}

}
