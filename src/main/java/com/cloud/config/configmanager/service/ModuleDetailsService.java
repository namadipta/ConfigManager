/**
 * 
 */
package com.cloud.config.configmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.config.configmanager.mapper.ModuleDetailsMapper;
import com.cloud.config.configmanager.model.entity.ModuleDetailsEntity;
import com.cloud.config.configmanager.model.service.ModuleDetailsServicePojo;
import com.cloud.config.configmanager.repo.ModuleDetailsRepo;

/**
 * @author namadipta
 *
 */
@Service
public class ModuleDetailsService {

	@Autowired
	private ModuleDetailsRepo moduleDetailsRepo;

	@Autowired
	private ModuleDetailsMapper moduleDetailsMapper;

	/**
	 * fetch all Module for an given appId
	 * 
	 * @param request
	 * @return
	 */
	public List<ModuleDetailsServicePojo> fetchAllModuleForAppID(final Long appId) {
		List<ModuleDetailsEntity> moduleDetails = moduleDetailsRepo.findByAppId(appId);
		return moduleDetailsMapper.map(moduleDetails);
	}

	/**
	 * Save a Module for an AppId
	 * 
	 * @param request
	 * @return
	 */
	public String saveModule(final ModuleDetailsServicePojo request) {
		ModuleDetailsEntity requestEntity = moduleDetailsMapper.saveMap(request);
		ModuleDetailsEntity savedData = moduleDetailsRepo.save(requestEntity);
		return savedData.getModuleId().toString();
	}

}
