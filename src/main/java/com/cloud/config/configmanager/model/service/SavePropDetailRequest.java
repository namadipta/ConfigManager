/**
 * 
 */
package com.cloud.config.configmanager.model.service;

import java.io.Serializable;
import java.util.List;

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
public class SavePropDetailRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6533246018036464078L;

	List<PropertyPojo> savePropDetails;

	private String selectedLabel;

	private String selectedProfile;

	private String selectedModule;

	private String appId;

	private String propVersion;
}
