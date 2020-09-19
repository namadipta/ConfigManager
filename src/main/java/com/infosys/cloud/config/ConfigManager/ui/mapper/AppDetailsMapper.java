/**
 * 
 */
package com.infosys.cloud.config.configmanager.ui.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.infosys.cloud.config.configmanager.ui.model.entity.AppDetailsEntity;
import com.infosys.cloud.config.configmanager.ui.model.service.AppDetailsServicePojo;

/**
 * @author namadipta
 *
 */
@Mapper(componentModel = "spring")
public interface AppDetailsMapper {

	/**
	 * @param findAll
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<AppDetailsServicePojo> map(List<AppDetailsEntity> requestList);

	/**
	 * @param request
	 * @return
	 */
	AppDetailsEntity saveRequest(AppDetailsServicePojo request);
}
