/**
 * 
 */
package com.infosys.cloud.config.configmanager.mapper.display.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

import com.infosys.cloud.config.configmanager.model.common.DropdownValue;
import com.infosys.cloud.config.configmanager.model.service.AppDetailsServicePojo;

/**
 * @author namadipta
 *
 */
@Mapper(componentModel = "spring")
public interface CommonMapper {

	/**
	 * @param appDetails
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<DropdownValue> mapForDropdown(List<AppDetailsServicePojo> appDetails);

	@Mappings({ @Mapping(source = "appId", target = "id"), @Mapping(source = "appName", target = "text") })
	DropdownValue mapForDropdowns(AppDetailsServicePojo appDetails);

}
