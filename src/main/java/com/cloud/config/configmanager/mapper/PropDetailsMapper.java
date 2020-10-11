/**
 * 
 */
package com.cloud.config.configmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cloud.config.configmanager.model.common.DropdownValue;
import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.entity.PropDetailsEntity;
import com.cloud.config.configmanager.model.service.PropDetailsServicePojo;
import com.cloud.config.configmanager.model.service.PropDetailsServiceResponse;
import com.cloud.config.configmanager.model.service.SavePropDetailRequest;

/**
 * @author namadipta
 *
 */
@Mapper(componentModel = "spring")
public interface PropDetailsMapper {

	/**
	 * @param request
	 * @return
	 */
	@Mappings({ @Mapping(target = "propData", ignore = true) })
	PropDetailsEntity prepareSaveRequest(PropDetailsServicePojo request);

	/**
	 * @param latestPropDetails
	 * @return
	 */
	@Mappings({ @Mapping(target = "propData", ignore = true) })
	PropDetailsServicePojo map(PropDetailsEntity latestPropDetails);

	/**
	 * @param latestPropDetails
	 * @return
	 */
	PropDetailsServiceResponse mapping(PropDetailsEntity latestPropDetails);

	/**
	 * @param selectedPropDetails
	 * @return
	 */
	PropDetailsServiceResponse mapResponse(PropDetailsServicePojo selectedPropDetails);

	/**
	 * @param properties
	 * @return
	 */
	@Mappings({ @Mapping(target = "modId", source = "selectedModule"),
			@Mapping(target = "labelId", source = "selectedLabel"),
			@Mapping(target = "profId", source = "selectedProfile") })
	PropDetailsServicePojo map(SavePropDetailRequest properties);

	/**
	 * @param properties
	 * @return
	 */
	@Mappings({ @Mapping(target = "propData", ignore = true), @Mapping(target = "modId", source = "selectedModule"),
			@Mapping(target = "labelId", source = "selectedLabel"),
			@Mapping(target = "profId", source = "selectedProfile") })
	PropDetailsEntity prepareSaveRequest(SavePropDetailRequest properties);

	/**
	 * @param selectedAppRequest
	 * @return
	 */
	@Mappings({ @Mapping(target = "modId", source = "selectedModule"),
			@Mapping(target = "labelId", source = "selectedLabel"),
			@Mapping(target = "profId", source = "selectedProfile") })
	PropDetailsServicePojo map(AppDisplayDetails selectedAppRequest);

	/**
	 * @param latestPropDetails
	 * @return
	 */
	@Mappings({ @Mapping(target = "id", source = "propVersion"), @Mapping(target = "text", source = "propVersion") })
	DropdownValue mapDropDown(PropDetailsEntity latestPropDetails);

	/**
	 * @param properties
	 * @return
	 */
	AppDisplayDetails mapper(SavePropDetailRequest properties);
}
