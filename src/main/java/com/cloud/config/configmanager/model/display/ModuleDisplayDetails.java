/**
 * 
 */
package com.cloud.config.configmanager.model.display;

import java.io.Serializable;

import lombok.Data;

/**
 * @author namadipta
 *
 */
@Data
public class ModuleDisplayDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4203146424080937134L;

	private String moduleName;

	private String moduleDisplayName;

}
