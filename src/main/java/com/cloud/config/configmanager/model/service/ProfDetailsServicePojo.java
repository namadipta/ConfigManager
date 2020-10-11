/**
 * 
 */
package com.cloud.config.configmanager.model.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @author namadipta
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ProfDetailsServicePojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6431262904322107688L;

	private Long profId;

	//private Long appId;

	private String profDisplayName;

	private String profName;

}
