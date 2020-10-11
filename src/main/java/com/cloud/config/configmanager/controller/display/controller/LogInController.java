package com.cloud.config.configmanager.controller.display.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloud.config.configmanager.model.service.UtilityService;

/**
 * @author namadipta
 *
 */
@Controller
public class LogInController {

	@Autowired
	private UtilityService utilityService;

	/**
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@GetMapping("/")
	public String login(ModelMap model, HttpSession session, HttpServletRequest request) {
		
		return "login";
	}

//	/**
//	 * @return
//	 */
//	@GetMapping("/login")
//	public String login() {
//
//		return "login";
//
//	}

	/**
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse res) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		handleLogOutResponse(request, res);
		return "logout";
	}

	private void handleLogOutResponse(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			cookie.setValue(null);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

	/**
	 * @return
	 */
	@GetMapping("/error")
	public String error() {
		return "error-404";
	}
}
