//package com.infosys.cloud.config.ConfigManager.ui;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * @author namadipta
// *
// */
//@Controller
//public class HomePageController {
//
//	@RequestMapping(value = "/")
//	public String index(ModelMap model) {
//		model.addAttribute("content", "dynamicDashBoard");
////		 HashMap<String,String> listOfModule= new HashMap<>();
////		 listOfModule.put("test","test1");
//		List <String> listOfModule = new ArrayList<>();
//		listOfModule.add("test");
//		listOfModule.add("test1");
//		DropdownValue d = new DropdownValue("test", "test");
//		DropdownValue d1 = new DropdownValue("test1", "test1");
//		Modules m = new Modules();
//		m.setListOfModule(listOfModule);
//		List<DropdownValue> listOfModules =  new ArrayList<>();
//		listOfModules.add(d);
//		listOfModules.add(d1);
//		m.setListOfModules(listOfModules);
//		model.addAttribute("modules", m);
//		return "index";
//	}
//
//}
