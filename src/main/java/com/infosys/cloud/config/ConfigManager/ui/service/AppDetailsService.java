/**
 * 
 */
package com.infosys.cloud.config.configmanager.ui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.cloud.config.ConfigManager.ui.mapper.AppDetailsMapper;
import com.infosys.cloud.config.ConfigManager.ui.model.entity.AppDetailsEntity;
import com.infosys.cloud.config.ConfigManager.ui.model.service.AppDetailsServicePojo;
import com.infosys.cloud.config.ConfigManager.ui.repo.AppDetailsRepo;

/**
 * @author namadipta
 *
 */
@Service
public class AppDetailsService {

	@Autowired
	private AppDetailsRepo appDetailsRepo;

	@Autowired
	private AppDetailsMapper appDetailsMapper;

	/**
	 * Fetch all App details for the organization
	 * 
	 * @return
	 */
	public List<AppDetailsServicePojo> fetchAllAppDetails() {
		return appDetailsMapper.map(appDetailsRepo.findAll());
	}

	/**
	 * Add new App details
	 * 
	 * @param request
	 * @return
	 */
	public String addNewApp(AppDetailsServicePojo request) {
		AppDetailsEntity savedDat = appDetailsRepo.save(appDetailsMapper.saveRequest(request));
		return savedDat.getAppId().toString();
	}
}