/**
 * 
 */
package com.cloud.config.configmanager.mapper.display.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

import com.cloud.config.configmanager.model.common.DropdownValue;
import com.cloud.config.configmanager.model.service.AppDetailsServicePojo;
import com.cloud.config.configmanager.model.service.LabelDetailsServicePojo;
import com.cloud.config.configmanager.model.service.ModuleDetailsServicePojo;
import com.cloud.config.configmanager.model.service.ProfDetailsServicePojo;

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

	/**
	 * @param envDetails
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<DropdownValue> mapForEnvDropdown(List<LabelDetailsServicePojo> envDetails);

	@Mappings({ @Mapping(source = "labelId", target = "id"), @Mapping(source = "labelDisplayName", target = "text") })
	DropdownValue mapForDropdowns(LabelDetailsServicePojo appDetails);

	/**
	 * @param moduleDetails
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<DropdownValue> mapForModule(List<ModuleDetailsServicePojo> moduleDetails);

	@Mappings({ @Mapping(source = "moduleId", target = "id"), @Mapping(source = "moduleDisplayName", target = "text") })
	DropdownValue mapForModuleDropdowns(ModuleDetailsServicePojo moduleDetails);

	/**
	 * @param profDetails
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<DropdownValue> mapForProf(List<ProfDetailsServicePojo> profDetails);

	@Mappings({ @Mapping(source = "profId", target = "id"), @Mapping(source = "profDisplayName", target = "text") })
	DropdownValue mapForModuleDropdowns(ProfDetailsServicePojo moduleDetails);

}
