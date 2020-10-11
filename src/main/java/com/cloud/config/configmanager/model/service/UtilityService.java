/**
 * 
 */
package com.cloud.config.configmanager.model.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cloud.config.configmanager.model.common.DropdownValue;
import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.service.LabelDetailsService;
import com.cloud.config.configmanager.service.ModuleDetailsService;
import com.cloud.config.configmanager.service.ProfDetailsService;
import com.cloud.config.configmanager.service.display.service.HomePageService;

/**
 * @author namadipta
 *
 */
@Component
public class UtilityService {

	@Autowired
	private LabelDetailsService labelDetailsService;

	@Autowired
	private ModuleDetailsService moduleDetailsService;

	@Autowired
	private ProfDetailsService profDetailsService;

	@Autowired
	private HomePageService homePageService;

	public AppDisplayDetails getAppDetailsFromCache() {

		AppDisplayDetails appDisplayDetails = getAppDetailsFromDB();
		return appDisplayDetails;
	}

	/**
	 * @param appId
	 */
	private AppDisplayDetails getAppDetailsFromDB(String... appId) {
		AppDisplayDetails appDisplayDetails = new AppDisplayDetails();
		List<LabelDetailsServicePojo> labelDetails = labelDetailsService.fetchAllEnvForAppID();
		appDisplayDetails.setListOflabels(homePageService.convertEnvDetailsForDropDown(labelDetails));
		List<ModuleDetailsServicePojo> moduleDetails = moduleDetailsService.fetchAllModuleForAppID();
		appDisplayDetails.setListOfModules(homePageService.convertModuleDetailsForDropDown(moduleDetails));
		List<ProfDetailsServicePojo> profDetails = profDetailsService.fetchAllProfForAppId();
		appDisplayDetails.setListOfProfs(homePageService.convertProfDetailsForDropDown(profDetails));

		return appDisplayDetails;
	}

	/**
	 * @param displayDetails
	 * @param modulename
	 * @return
	 */
	public Long findModuleIdBasedOnName(AppDisplayDetails displayDetails, String modulename) {
		if (Objects.nonNull(displayDetails) && !CollectionUtils.isEmpty(displayDetails.getListOfModules())) {
			Optional<DropdownValue> module = displayDetails.getListOfModules().parallelStream()
					.filter(i -> StringUtils.equalsIgnoreCase(i.getText(), modulename)).findFirst();
			return module.isPresent() ? Long.parseLong(module.get().getId()) : null;
		}
		return null;
	}

	/**
	 * @param displayDetails
	 * @param profile
	 * @return
	 */
	public Long findProfileIdBasedOnName(AppDisplayDetails displayDetails, String profile) {
		if (Objects.nonNull(displayDetails) && !CollectionUtils.isEmpty(displayDetails.getListOfProfs())) {
			Optional<DropdownValue> prof = displayDetails.getListOfProfs().parallelStream()
					.filter(i -> StringUtils.equalsIgnoreCase(i.getText(), profile)).findFirst();
			return prof.isPresent() ? Long.parseLong(prof.get().getId()) : null;
		}
		return null;
	}

	/**
	 * @param displayDetails
	 * @param label
	 * @return
	 */
	public Long findLabelIdBasedOnName(AppDisplayDetails displayDetails, String label) {
		if (Objects.nonNull(displayDetails) && !CollectionUtils.isEmpty(displayDetails.getListOflabels())) {
			Optional<DropdownValue> env = displayDetails.getListOflabels().parallelStream()
					.filter(i -> StringUtils.equalsIgnoreCase(i.getText(), label)).findFirst();
			return env.isPresent() ? Long.parseLong(env.get().getId()) : null;
		}
		return null;
	}
}
