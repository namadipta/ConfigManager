/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.display.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.infosys.cloud.config.ConfigManager.ui.display.common.DropdownValue;
import com.infosys.cloud.config.ConfigManager.ui.model.service.AppDetailsServicePojo;

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

}
