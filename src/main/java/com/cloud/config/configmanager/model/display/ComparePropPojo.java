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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
public class ComparePropPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2486485984017331583L;

	private String appId;

	private String targetSelectedModule;

	private String targetSelectedProfile;

	private String targetSelectedLabel;

	private String targetSelectedPropVersion;

	private String sourceSelectedModule;

	private String sourceSelectedProfile;

	private String sourceSelectedLabel;

	private String sourceSelectedPropVersion;

}
