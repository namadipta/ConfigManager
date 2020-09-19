/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.display.common;

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
public class DropdownValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8571012825303976077L;
	String id;
	String text;
}
