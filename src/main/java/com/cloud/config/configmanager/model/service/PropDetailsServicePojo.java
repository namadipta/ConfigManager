/**
 * 
 */
package com.cloud.config.configmanager.model.service;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Map;

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
public class PropDetailsServicePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3663936707961971113L;

	private Long propId;

	private Long appId;

	private Long propVersion;
	
	private Long labelId;

	private Long profId;

	private Long modId;

	private ZonedDateTime addedDate;

	private ZonedDateTime modifiedDate;

	private String addedBy;

	private String modifiedBy;

	private Map<String, PropDataPojo> propData;
}
