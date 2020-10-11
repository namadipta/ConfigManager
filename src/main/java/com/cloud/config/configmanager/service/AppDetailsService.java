///**
// * 
// */
//package com.cloud.config.configmanager.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.cloud.config.configmanager.mapper.AppDetailsMapper;
//import com.cloud.config.configmanager.model.display.AppDisplayDetails;
//import com.cloud.config.configmanager.model.entity.AppDetailsEntity;
//import com.cloud.config.configmanager.model.service.AppDetailsServicePojo;
//import com.cloud.config.configmanager.model.service.SavePropDetailRequest;
//import com.cloud.config.configmanager.repo.AppDetailsRepo;
//
///**
// * @author namadipta
// *
// */
//@Service
//public class AppDetailsService {
//
//	@Autowired
//	private AppDetailsRepo appDetailsRepo;
//
//	@Autowired
//	private AppDetailsMapper appDetailsMapper;
//
//	/**
//	 * Fetch all App details for the organization
//	 * 
//	 * @return
//	 */
//	public List<AppDetailsServicePojo> fetchAllAppDetails() {
//		return appDetailsMapper.map(appDetailsRepo.findAll());
//	}
//
//	/**
//	 * Add new App details
//	 * 
//	 * @param request
//	 * @return
//	 */
//	public String addNewApp(AppDetailsServicePojo request) {
//		AppDetailsEntity savedDat = appDetailsRepo.save(appDetailsMapper.saveRequest(request));
//		return savedDat.getAppId().toString();
//	}
//
//	/**
//	 * @param properties
//	 * @return
//	 */
//	public AppDisplayDetails prepareSelectedAppRequest(SavePropDetailRequest properties) {
//		return appDetailsMapper.map(properties);
//	}
//	
//}
