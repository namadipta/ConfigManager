/**
 * 
 */
package com.cloud.config.configmanager.service.display.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.config.configmanager.mapper.display.mapper.CommonMapper;
import com.cloud.config.configmanager.model.common.DropdownValue;
import com.cloud.config.configmanager.model.service.AppDetailsServicePojo;
import com.cloud.config.configmanager.model.service.LabelDetailsServicePojo;
import com.cloud.config.configmanager.model.service.ModuleDetailsServicePojo;
import com.cloud.config.configmanager.model.service.ProfDetailsServicePojo;

/**
 * @author namadipta
 *
 */
@Service
public class HomePageService {

	@Autowired
	private CommonMapper commonMapper;

	/**
	 * @param appDetails
	 * @return
	 */
	public List<DropdownValue> convertAppDetailsForDropDown(final List<AppDetailsServicePojo> appDetails) {
		return commonMapper.mapForDropdown(appDetails);
	}

	/**
	 * @param envDetails
	 * @return
	 */
	public List<DropdownValue> convertEnvDetailsForDropDown(List<LabelDetailsServicePojo> envDetails) {
		return commonMapper.mapForEnvDropdown(envDetails);
	}

	/**
	 * @param moduleDetails
	 * @return
	 */
	public List<DropdownValue> convertModuleDetailsForDropDown(List<ModuleDetailsServicePojo> moduleDetails) {
		return commonMapper.mapForModule(moduleDetails);
	}

	/**
	 * @param profDetails
	 * @return
	 */
	public List<DropdownValue> convertProfDetailsForDropDown(List<ProfDetailsServicePojo> profDetails) {
		return commonMapper.mapForProf(profDetails);
	}
}
