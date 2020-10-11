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
public class PropDetailsServiceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5271713137054391035L;

	private Long propVersion;

	private Long propId;

	private ZonedDateTime modifiedDate;

	private String modifiedBy;

}
