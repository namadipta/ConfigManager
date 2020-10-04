/**
 * 
 */
package com.cloud.config.configmanager.model.display;

import java.io.Serializable;
import java.util.List;

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
public class AllUserAccessDetailsPojo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 351303505999358980L;

	private List<UserAccessResponse> listOfUser;

}
