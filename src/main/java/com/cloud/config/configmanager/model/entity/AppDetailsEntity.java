///**
// * 
// */
//package com.cloud.config.configmanager.model.entity;
//
//import java.time.ZonedDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.CreationTimestamp;
//
//import lombok.Data;
//
///**
// * @author namadipta
// *
// */
//@Entity
//@Table(name = "app_details")
//@Data
//public class AppDetailsEntity {
//
//	@Column(name = "app_id")
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long appId;
//
//	@Column(name = "app_name")
//	private String appName;
//
//	@Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//	@CreationTimestamp
//	private ZonedDateTime addedDate;
//
//}
