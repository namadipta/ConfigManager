/**
 * 
 */
package com.infosys.cloud.config.configmanager.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.infosys.cloud.config.configmanager.model.entity.ModuleDetailsEntity;
import com.infosys.cloud.config.configmanager.model.service.ModuleDetailsServicePojo;

/**
 * @author namadipta
 *
 */
@Mapper(componentModel = "spring")
public interface ModuleDetailsMapper {

	/**
	 * @param moduleDetails
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<ModuleDetailsServicePojo> map(List<ModuleDetailsEntity> moduleDetails);

	/**
	 * @param request
	 * @return
	 */
	ModuleDetailsEntity saveMap(ModuleDetailsServicePojo request);

}
