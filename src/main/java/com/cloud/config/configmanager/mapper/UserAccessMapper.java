/**
 * 
 */
package com.cloud.config.configmanager.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.cloud.config.configmanager.model.display.UserAccessResponse;
import com.cloud.config.configmanager.model.entity.UserAccessEntity;

/**
 * @author namadipta
 *
 */
@Mapper(componentModel = "spring")
public interface UserAccessMapper {

	/**
	 * @param userAccessList
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	public List<UserAccessResponse> map(List<UserAccessEntity> userAccessList);

	UserAccessResponse mapResponse(UserAccessEntity entity);

	/**
	 * @param listOfUser
	 * @return
	 */
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	public List<UserAccessEntity> saveMap(List<UserAccessResponse> listOfUser);

	UserAccessEntity mapReQuest(UserAccessResponse request);

}
