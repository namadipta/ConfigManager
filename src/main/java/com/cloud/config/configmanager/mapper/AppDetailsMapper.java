///**
// * 
// */
//package com.cloud.config.configmanager.mapper;
//
//import java.util.List;
//
//import org.mapstruct.IterableMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.NullValueMappingStrategy;
//
//import com.cloud.config.configmanager.model.display.AppDisplayDetails;
//import com.cloud.config.configmanager.model.entity.AppDetailsEntity;
//import com.cloud.config.configmanager.model.service.AppDetailsServicePojo;
//import com.cloud.config.configmanager.model.service.SavePropDetailRequest;
//
///**
// * @author namadipta
// *
// */
//@Mapper(componentModel = "spring")
//public interface AppDetailsMapper {
//
//	/**
//	 * @param findAll
//	 * @return
//	 */
//	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
//	List<AppDetailsServicePojo> map(List<AppDetailsEntity> requestList);
//
//	/**
//	 * @param request
//	 * @return
//	 */
//	AppDetailsEntity saveRequest(AppDetailsServicePojo request);
//
//	/**
//	 * @param properties
//	 * @return
//	 */
//	AppDisplayDetails map(SavePropDetailRequest properties);
//}
