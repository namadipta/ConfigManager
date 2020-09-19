/**
 * 
 */
package com.infosys.cloud.config.configmanager.model.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * @author namadipta
 *
 */
@Entity
@Table(name = "module_details")
@Data
public class ModuleDetailsEntity {

	@Column(name = "mod_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moduleId;

	@Column(name = "mod_display_name")
	private String moduleDisplayName;

	@Column(name = "mod_name")
	private String moduleName;

	@Column(name = "added_by")
	private String addedBy;

	@Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private ZonedDateTime addedDate;

	@Column(name = "app_id")
	private Long appId;

}
