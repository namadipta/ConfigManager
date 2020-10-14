/**
 * 
 */
package com.cloud.config.configmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.config.configmanager.mapper.ProfDetailsMapper;
import com.cloud.config.configmanager.model.display.AddConfigDetails;
import com.cloud.config.configmanager.model.entity.ProfDetailsEntity;
import com.cloud.config.configmanager.model.service.ProfDetailsServicePojo;
import com.cloud.config.configmanager.repo.ProfDetailsRepo;

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
	public List<ProfDetailsServicePojo> fetchAllProfForAppId() {
		List<ProfDetailsEntity> profDetails = profDetailsRepo.findAll();
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

	/**
	 * @param request
	 */
	public String saveProf(AddConfigDetails request) {
		return saveProf(profDetailsMapper.mapper(request));
	}

}
