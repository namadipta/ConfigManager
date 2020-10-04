/**
 * 
 */
package com.cloud.config.configmanager.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cloud.config.configmanager.mapper.UserAccessMapper;
import com.cloud.config.configmanager.model.display.AllUserAccessDetailsPojo;
import com.cloud.config.configmanager.model.display.UserAccessResponse;
import com.cloud.config.configmanager.model.entity.UserAccessEntity;
import com.cloud.config.configmanager.repo.UserAccessRepo;

/**
 * @author namadipta
 *
 */
@Service
public class UserAccessService {

	@Autowired
	private UserAccessRepo userAccessRepo;

	@Autowired
	private UserAccessMapper userAccessMapper;

	/**
	 * @return
	 */
	public List<UserAccessResponse> findAll() {
		List<UserAccessEntity> userAccessList = userAccessRepo.findAll();
		return userAccessMapper.map(userAccessList);
	}

	/**
	 * @param userDetails
	 * @return
	 */
	public String saveUserDetails(AllUserAccessDetailsPojo userDetails) {
		if (Objects.nonNull(userDetails) && !CollectionUtils.isEmpty(userDetails.getListOfUser())) {
			List<UserAccessEntity> userAccessList = userAccessMapper.saveMap(userDetails.getListOfUser());
			List<UserAccessEntity> existingList = userAccessRepo.findAll();
			if (!CollectionUtils.isEmpty(existingList)) {
				List<UserAccessEntity> updatedList = userAccessList.stream().map((e) -> {
					return existingList.stream().filter(i -> StringUtils.equalsIgnoreCase(i.getEmail(), e.getEmail()))
							.findFirst().map(k -> {
								e.setUserId(k.getUserId());
								return e;
							}).orElse(e);
				}).collect(Collectors.toList());
				userAccessRepo.saveAll(updatedList.parallelStream().filter(i -> Objects.nonNull(i.getUserId()))
						.collect(Collectors.toList()));
			}
		}
		return null;
	}
}
