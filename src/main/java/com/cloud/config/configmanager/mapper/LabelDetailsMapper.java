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
import com.cloud.config.configmanager.model.entity.LabelDetailsEntity;
import com.cloud.config.configmanager.model.service.LabelDetailsServicePojo;

/**
 * @author namadipta
 *
 */
@Mapper(componentModel = "spring")
public interface LabelDetailsMapper {

	/**
	 * @param envDetails
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<LabelDetailsServicePojo> map(List<LabelDetailsEntity> envDetails);

	/**
	 * @param request
	 * @return
	 */
	LabelDetailsEntity saveMap(LabelDetailsServicePojo request);

	/**
	 * @param request
	 * @return
	 */
	@Mappings({ @Mapping(target = "labelDisplayName", source = "displayName"),
			@Mapping(target = "labelName", source = "name") })
	LabelDetailsServicePojo mapper(AddConfigDetails request);

}
