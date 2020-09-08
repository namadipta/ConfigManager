package com.infosys.cloud.config.ConfigManager.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author namadipta
 *
 */
@Controller
public class HomePageController {

	@RequestMapping(value = "/")
	public String index(ModelMap model) {
		model.addAttribute("content", "dashBoardDynamic");
		return "index";
	}

}
