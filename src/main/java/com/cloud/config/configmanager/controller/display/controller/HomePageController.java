/**
 * 
 */
package com.cloud.config.configmanager.controller.display.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.display.AppsDisplayRequest;
import com.cloud.config.configmanager.model.service.AppDetailsServicePojo;
import com.cloud.config.configmanager.model.service.PropDetailsServicePojo;
import com.cloud.config.configmanager.model.service.PropDetailsServiceResponse;
import com.cloud.config.configmanager.model.service.PropertyPojo;
import com.cloud.config.configmanager.model.service.SavePropDetailRequest;
import com.cloud.config.configmanager.model.service.UtilityService;
import com.cloud.config.configmanager.service.AppDetailsService;
import com.cloud.config.configmanager.service.FileUploadService;
import com.cloud.config.configmanager.service.PropDetailsService;
import com.cloud.config.configmanager.service.display.service.HomePageService;

/**
 * @author namadipta
 *
 */
@Controller
public class HomePageController {

	@Autowired
	private AppDetailsService appDetailsService;

	@Autowired
	private HomePageService homePageService;

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private PropDetailsService propDetailsService;

	@Autowired
	private FileUploadService fileUploadService;

	/**
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/apps")
	public String index(ModelMap model) {
		List<AppDetailsServicePojo> listofApp = appDetailsService.fetchAllAppDetails();
		AppsDisplayRequest modelData = new AppsDisplayRequest();
		modelData.setListOfApps(homePageService.convertAppDetailsForDropDown(listofApp));
		model.addAttribute("appData", modelData);
		return "appselector";
	}

	/**
	 * @param model
	 * @param selectedAppRequest
	 * @return
	 */
	@PostMapping(value = "/appdetails")
	public String loadAppDetails(ModelMap model, @ModelAttribute("appId") AppsDisplayRequest selectedAppRequest) {
		String selectedAppId = selectedAppRequest.getAppId();

		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache(selectedAppId));
		model.addAttribute("appId", selectedAppId);
		model.addAttribute("content", "propdetails");
		model.addAttribute("propDetails", null);
		model.addAttribute("selectedAppRequest", new AppDisplayDetails());

		return "index";
	}

	/**
	 * TODO : Module mandatory check
	 * 
	 * @param model
	 * @param selectedAppRequest
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@PostMapping(value = "/propdetails")
	public String loadPropDetails(ModelMap model,
			@ModelAttribute("appDisplayDetails") AppDisplayDetails selectedAppRequest)
			throws JsonMappingException, JsonProcessingException {
		PropDetailsServicePojo request = new PropDetailsServicePojo();
		request.setProfId(Long.parseLong(selectedAppRequest.getSelectedProfile()));
		request.setLabelId(Long.parseLong(selectedAppRequest.getSelectedLabel()));
		request.setModId(Long.parseLong(selectedAppRequest.getSelectedModule()));
		request.setAppId(Long.parseLong(selectedAppRequest.getAppId()));

		PropDetailsServicePojo selectedPropDetails = propDetailsService.fetchLatestPropDetails(request);

		PropDetailsServiceResponse propDetailsResponse = propDetailsService
				.prepareResponseForRender(selectedPropDetails);
		model.addAttribute("propDetails", propDetailsResponse);
		SavePropDetailRequest savePropDetailRequest = new SavePropDetailRequest();
		List<PropertyPojo> propList = propDetailsService.processProperties(selectedPropDetails);
		if (CollectionUtils.isEmpty(propList)) {
			propList = Arrays.asList(new PropertyPojo());
			savePropDetailRequest.setSavePropDetails(propList);
		} else {
			savePropDetailRequest.setSavePropDetails(propList);
		}

		model.addAttribute("saveProperties", savePropDetailRequest);
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache(selectedAppRequest.getAppId()));
		model.addAttribute("selectedAppRequest", selectedAppRequest);
		model.addAttribute("content", "propdetails");

		return "index";
	}

	/**
	 * @param model
	 * @param savePropDetailsRequest
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@PostMapping(value = "/savepropdetails")
	public String savePropDetails(ModelMap model, @ModelAttribute SavePropDetailRequest properties)
			throws JsonMappingException, JsonProcessingException {
		String latestPropId = propDetailsService.savePropDetails(properties);
		PropDetailsServicePojo savePropDetails = propDetailsService.fetchLatestPropDetails(latestPropId);

		PropDetailsServiceResponse propDetailsResponse = propDetailsService.prepareResponseForRender(savePropDetails);

		model.addAttribute("propDetails", propDetailsResponse);
		SavePropDetailRequest savePropDetailRequest = new SavePropDetailRequest();
		savePropDetailRequest.setSavePropDetails(propDetailsService.processProperties(savePropDetails));
		model.addAttribute("saveProperties", savePropDetailRequest);
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache(properties.getAppId()));
		AppDisplayDetails selectedAppRequest = appDetailsService.prepareSelectedAppRequest(properties);
		model.addAttribute("selectedAppRequest", selectedAppRequest);
		model.addAttribute("content", "propdetails");

		return "index";
	}

	/**
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/loadbulkpage")
	public String uploadProperties(ModelMap model,
			@ModelAttribute("appDisplayDetails") AppDisplayDetails selectedAppRequest) {

		model.addAttribute("propDetails", new PropDetailsServiceResponse());
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache(selectedAppRequest.getAppId()));
		model.addAttribute("selectedAppRequest", selectedAppRequest);
		model.addAttribute("bulkpage", "true");
		model.addAttribute("content", "propdetails");
		SavePropDetailRequest savePropDetailRequest = new SavePropDetailRequest();
		savePropDetailRequest.setSavePropDetails(Arrays.asList(new PropertyPojo()));
		model.addAttribute("saveProperties", savePropDetailRequest);
		return "index";
	}

	/**
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/uploadproperties")
	public String uploadProperties(ModelMap model, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes,
			@ModelAttribute("appDisplayDetails") AppDisplayDetails selectedAppRequest) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		SavePropDetailRequest savePropDetailRequest = new SavePropDetailRequest();
		List<PropertyPojo> propList = fileUploadService.uploadFile(file);
		if (CollectionUtils.isEmpty(propList)) {
			propList = Arrays.asList(new PropertyPojo());
			savePropDetailRequest.setSavePropDetails(propList);
		} else {
			savePropDetailRequest.setSavePropDetails(propList);
		}
		model.addAttribute("propDetails", propDetailsService.fetchLatestPropVersion(selectedAppRequest));
		model.addAttribute("saveProperties", savePropDetailRequest);
		model.addAttribute("appDisplayDetails", utilityService.getAppDetailsFromCache(selectedAppRequest.getAppId()));
		model.addAttribute("selectedAppRequest", selectedAppRequest);
		model.addAttribute("content", "propdetails");
		model.addAttribute("bulkpage", "true");
		return "index";
	}

}
