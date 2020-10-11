/**
 * 
 */
package com.cloud.config.configmanager.model.display;

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
public class UserAccessResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7813866113522376834L;

	private String name;

	private Boolean viewAccess;

	private Boolean admin;
}
