/**
 * 
 */
package com.cloud.config.configmanager.session;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author namadipta
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private OAuth2UserService userService;

	@Autowired
	private MyAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/config").antMatcher("/oauth2/**").authorizeRequests().anyRequest().authenticated().and()
//				.csrf().csrfTokenRepository(csrfTokenRepository()).and()
//				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class).logout().logoutUrl("/logout").permitAll().and()
//				.formLogin().loginPage("/login").defaultSuccessUrl("/").and().oauth2Login().loginPage("/login")
//				.userInfoEndpoint().userService(userService).and().successHandler(authenticationSuccessHandler);
		http.authorizeRequests().antMatchers("/oauth2/**").permitAll().antMatchers("/config/**").authenticated()
				.anyRequest().permitAll().and().formLogin().loginPage("/login").usernameParameter("name").permitAll()
				.defaultSuccessUrl("/").and().oauth2Login().loginPage("/login").defaultSuccessUrl("/appdetails", true)
				.userInfoEndpoint().userService(userService).and().successHandler(authenticationSuccessHandler).and()
				.logout().permitAll();
	}

	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = new Cookie("XSRF-TOKEN", csrf.getToken());
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				filterChain.doFilter(request, response);
			}
		};
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}
