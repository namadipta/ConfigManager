/**
 * 
 */
package com.cloud.config.configmanager.model.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author namadipta
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
public class PropertyPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4236347824853261716L;

	private String id;
	private String text;
	private Boolean hide = Boolean.FALSE;
}
