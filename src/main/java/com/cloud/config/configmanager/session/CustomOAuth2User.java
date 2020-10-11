/**
 * 
 */
package com.cloud.config.configmanager.session;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author namadipta
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {

	private OAuth2User user;

	@Override
	public Map<String, Object> getAttributes() {
		return user.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public String getName() {
		return user.getAttribute("login");
	}

	public String getLogIn() {
		return user.getAttribute("login");
	}

}
