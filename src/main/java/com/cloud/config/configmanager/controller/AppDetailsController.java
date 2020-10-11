///**
// * 
// */
//package com.cloud.config.configmanager.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cloud.config.configmanager.model.service.AppDetailsServicePojo;
//import com.cloud.config.configmanager.service.AppDetailsService;
//
///**
// * @author namadipta
// *
// */
//@RestController
//public class AppDetailsController {
//
//	@Autowired
//	private AppDetailsService appDetailsService;
//
//	@GetMapping("/app/details")
//	public List<AppDetailsServicePojo> fetchAllAppDetails() {
//		return appDetailsService.fetchAllAppDetails();
//	}
//
//	@PostMapping("/app/details")
//	public String addNewApp(@RequestBody AppDetailsServicePojo request) {
//		return appDetailsService.addNewApp(request);
//	}
//}
