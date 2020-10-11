/**
 * 
 */
package com.cloud.config.configmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.config.configmanager.mapper.LabelDetailsMapper;
import com.cloud.config.configmanager.model.entity.LabelDetailsEntity;
import com.cloud.config.configmanager.model.service.LabelDetailsServicePojo;
import com.cloud.config.configmanager.repo.LabelDetailsRepo;

/**
 * @author namadipta
 *
 */
@Service
public class LabelDetailsService {

	@Autowired
	private LabelDetailsRepo envDetailsRepo;

	@Autowired
	private LabelDetailsMapper envDetailsMapper;

	/**
	 * fetch all Module for an given appId
	 * 
	 * @param request
	 * @return
	 */
	public List<LabelDetailsServicePojo> fetchAllEnvForAppID() {
		List<LabelDetailsEntity> envDetails = envDetailsRepo.findAll();
		return envDetailsMapper.map(envDetails);
	}

	/**
	 * Save a Module for an AppId
	 * 
	 * @param request
	 * @return
	 */
	public String saveEnv(final LabelDetailsServicePojo request) {
		LabelDetailsEntity requestEntity = envDetailsMapper.saveMap(request);
		LabelDetailsEntity savedData = envDetailsRepo.save(requestEntity);
		return savedData.getLabelId().toString();
	}

}
