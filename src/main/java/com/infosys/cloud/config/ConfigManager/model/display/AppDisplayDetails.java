/**
 * 
 */
package com.infosys.cloud.config.configmanager.model.display;

import java.io.Serializable;
import java.util.List;

import com.infosys.cloud.config.configmanager.model.common.DropdownValue;

import lombok.Data;

/**
 * @author namadipta
 *
 */
@Data
public class AppDisplayDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9107226074149677158L;

	private List<DropdownValue> listOfEnvs;

	private List<DropdownValue> listOfProfs;

	private List<DropdownValue> listOfModules;

	private String selectedEnv;

	private String selectedProfile;

	private String selectedModule;

}
