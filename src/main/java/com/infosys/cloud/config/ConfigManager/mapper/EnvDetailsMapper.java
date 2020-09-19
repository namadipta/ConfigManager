/**
 * 
 */
package com.infosys.cloud.config.configmanager.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.infosys.cloud.config.configmanager.model.entity.EnvDetailsEntity;
import com.infosys.cloud.config.configmanager.model.service.EnvDetailsServicePojo;

/**
 * @author namadipta
 *
 */
@Mapper(componentModel = "spring")
public interface EnvDetailsMapper {

	/**
	 * @param envDetails
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<EnvDetailsServicePojo> map(List<EnvDetailsEntity> envDetails);

	/**
	 * @param request
	 * @return
	 */
	EnvDetailsEntity saveMap(EnvDetailsServicePojo request);

}
