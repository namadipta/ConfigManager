/**
 * 
 */
package com.cloud.config.configmanager.model.display;

import java.io.Serializable;
import java.util.List;

import com.cloud.config.configmanager.model.common.DropdownValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author namadipta
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppDisplayDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9107226074149677158L;

	private List<DropdownValue> listOflabels;

	private List<DropdownValue> listOfProfs;

	private List<DropdownValue> listOfModules;

	private List<DropdownValue> listOfSourcePropVersion;

	private List<DropdownValue> listOfTargetPropVersion;

	private String selectedLabel;

	private String selectedProfile;

	private String selectedModule;

	private String moduleId;

	private String appId;

}
