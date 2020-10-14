/**
 * 
 */
package com.cloud.config.configmanager.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

import com.cloud.config.configmanager.model.display.AddConfigDetails;
import com.cloud.config.configmanager.model.entity.ProfDetailsEntity;
import com.cloud.config.configmanager.model.service.ProfDetailsServicePojo;

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

	/**
	 * @param request
	 * @return
	 */
	@Mappings({ @Mapping(target = "profDisplayName", source = "displayName"),
			@Mapping(target = "profName", source = "name") })
	ProfDetailsServicePojo mapper(AddConfigDetails request);

}
