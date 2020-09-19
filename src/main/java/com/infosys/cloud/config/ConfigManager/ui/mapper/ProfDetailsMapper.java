/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.infosys.cloud.config.ConfigManager.ui.model.entity.ProfDetailsEntity;
import com.infosys.cloud.config.ConfigManager.ui.model.service.ProfDetailsServicePojo;

/**
 * @author namadipta
 *
 */
@Mapper(componentModel = "spring")
public interface ProfDetailsMapper {

	/**
	 * @param ProfDetails
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<ProfDetailsServicePojo> map(List<ProfDetailsEntity> ProfDetails);

	/**
	 * @param request
	 * @return
	 */
	ProfDetailsEntity saveMap(ProfDetailsServicePojo request);

}
