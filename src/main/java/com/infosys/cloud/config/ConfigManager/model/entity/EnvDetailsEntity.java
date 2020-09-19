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
@Table(name = "env_details")
@Data
public class EnvDetailsEntity {

	@Column(name = "env_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long envId;

	@Column(name = "app_id")
	private Long appId;

	@Column(name = "env_display_name")
	private String envDisplayName;

	@Column(name = "env_name")
	private String envName;

	@Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private ZonedDateTime addedDate;

}
