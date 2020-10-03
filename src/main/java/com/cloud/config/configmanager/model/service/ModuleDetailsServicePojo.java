/**
 * 
 */
package com.cloud.config.configmanager.model.service;

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
public class ModuleDetailsServicePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8887987573311073612L;

	private Long moduleId;

	private Long appId = 1L;

	private String moduleDisplayName;

	private String moduleName;

	private String addedBy;

	private ZonedDateTime addedDate;

}
