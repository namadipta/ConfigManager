/**
 * 
 */
package com.cloud.config.configmanager.model.display;

import java.io.Serializable;
import java.util.List;

import com.cloud.config.configmanager.model.common.DropdownValue;

import lombok.Data;

/**
 * @author namadipta
 *
 */
@Data
public class CompareDetailsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8057435286743170111L;

	private List<DropdownValue> removedProps;

	private List<DropdownValue> newProps;

	private List<ChangedData> changedValues;
}
