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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloud.config.configmanager.model.display.AppDisplayDetails;
import com.cloud.config.configmanager.model.service.PropDetailsServiceResponse;
import com.cloud.config.configmanager.model.service.PropertyPojo;
import com.cloud.config.configmanager.model.service.SavePropDetailRequest;
import com.cloud.config.configmanager.model.service.UtilityService;
import com.cloud.config.configmanager.service.FileUploadService;
import com.cloud.config.configmanager.service.PropDetailsService;

/**
 * @author namadipta
 *
 */
@Controller
public class BulkController {

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private PropDetailsService propDetailsService;

	@Autowired
	private FileUploadService fileUploadService;

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
		model.addAttribute("appId", selectedAppRequest.getAppId());
		model.addAttribute("bulkpage", "true");
		model.addAttribute("content", "propdetails");
		SavePropDetailRequest savePropDetailRequest = new SavePropDetailRequest();
		savePropDetailRequest.setSavePropDetails(Arrays.asList(new PropertyPojo()));
		model.addAttribute("saveProperties", savePropDetailRequest);
		model.addAttribute("appdetails", "true");
		return "configHome";
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
		model.addAttribute("appdetails", "true");
		return "configHome";
	}
}
