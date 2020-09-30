/**
 * 
 */
package com.cloud.config.configmanager.model.display;

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
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class CompareDetailsPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6471508678435874877L;

	private String selectedModule;

	private String selectedProfile;

	private String selectedLabel;

	private String selectedPropVersion;

}
