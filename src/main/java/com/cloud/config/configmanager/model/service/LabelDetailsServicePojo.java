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
public class LabelDetailsServicePojo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8609998443373220704L;

	private Long labelId;

	private Long appId;

	private String labelDisplayName;

	private String labelName;

	private ZonedDateTime addedDate;
}
