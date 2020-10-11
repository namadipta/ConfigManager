/**
 * 
 */
package com.cloud.config.configmanager.session;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.cloud.config.configmanager.model.display.UserAccessResponse;
import com.cloud.config.configmanager.service.UserAccessService;

/**
 * @author namadipta
 *
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserAccessService userService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomOAuth2User userDetails = (CustomOAuth2User) authentication.getPrincipal();
		UserAccessResponse userAccess = null;
		if (userService.count() <= 0) {
			userAccess = userService.saveNewUserAsAdmin(userDetails.getName());
		} else {
			userAccess = userService.findByName(userDetails.getName());
			if (Objects.isNull(userAccess)) {
				userAccess = userService.saveNewUser(userDetails.getName());
			}
		}

		handle(request, response, userAccess);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, UserAccessResponse userAccess)
			throws IOException {

		String targetUrl = determineTargetUrl(userAccess);

		if (response.isCommitted()) {
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final UserAccessResponse userAccess) {

		if (Objects.nonNull(userAccess)) {
			return "/appdetails";
		}

		throw new IllegalStateException();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
