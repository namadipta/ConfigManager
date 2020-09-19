/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.model.service;

import java.io.Serializable;
import java.time.ZonedDateTime;

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
public class EnvDetailsServicePojo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8609998443373220704L;

	private Long envId;

	private Long appId;

	private String envDisplayName;

	private String envName;

	private ZonedDateTime addedDate;
}
