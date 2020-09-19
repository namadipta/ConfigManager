/**
 * 
 */
package com.infosys.cloud.config.configmanager.ui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.cloud.config.configmanager.ui.mapper.ProfDetailsMapper;
import com.infosys.cloud.config.configmanager.ui.model.entity.ProfDetailsEntity;
import com.infosys.cloud.config.configmanager.ui.model.service.ProfDetailsServicePojo;
import com.infosys.cloud.config.configmanager.ui.repo.ProfDetailsRepo;

/**
 * @author namadipta
 *
 */
@Service
public class ProfDetailsService {

	@Autowired
	private ProfDetailsRepo profDetailsRepo;

	@Autowired
	private ProfDetailsMapper profDetailsMapper;

	/**
	 * fetch all Module for an given appId
	 * 
	 * @param request
	 * @return
	 */
	public List<ProfDetailsServicePojo> fetchAllProfForAppID(final Long appId) {
		List<ProfDetailsEntity> profDetails = profDetailsRepo.findByAppId(appId);
		return profDetailsMapper.map(profDetails);
	}

	/**
	 * Save a Module for an AppId
	 * 
	 * @param request
	 * @return
	 */
	public String saveProf(final ProfDetailsServicePojo request) {
		ProfDetailsEntity requestEntity = profDetailsMapper.saveMap(request);
		ProfDetailsEntity savedData = profDetailsRepo.save(requestEntity);
		return savedData.getProfId().toString();
	}

}
