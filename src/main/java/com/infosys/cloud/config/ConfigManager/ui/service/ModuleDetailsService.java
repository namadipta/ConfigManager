/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.cloud.config.ConfigManager.ui.mapper.ModuleDetailsMapper;
import com.infosys.cloud.config.ConfigManager.ui.model.entity.ModuleDetailsEntity;
import com.infosys.cloud.config.ConfigManager.ui.model.service.ModuleDetailsServicePojo;
import com.infosys.cloud.config.ConfigManager.ui.repo.ModuleDetailsRepo;

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
