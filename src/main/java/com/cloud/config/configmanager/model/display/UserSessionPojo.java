/**
 * 
 */
package com.cloud.config.configmanager.model.display;

import java.io.Serializable;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

/**
 * @author namadipta
 *
 */
@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserSessionPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1030329454812631779L;

	private String name;

	private String role;

}
