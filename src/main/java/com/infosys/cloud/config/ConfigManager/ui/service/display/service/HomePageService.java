/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.display.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.cloud.config.ConfigManager.ui.display.common.DropdownValue;
import com.infosys.cloud.config.ConfigManager.ui.display.mapper.CommonMapper;
import com.infosys.cloud.config.ConfigManager.ui.model.service.AppDetailsServicePojo;

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
}
