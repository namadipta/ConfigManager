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
public class ChangedData implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 784590964200128026L;

	private String id;

	private String oldValue;

	private String newValue;

}
