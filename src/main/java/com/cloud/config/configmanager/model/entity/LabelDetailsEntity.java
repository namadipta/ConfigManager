/**
 * 
 */
package com.cloud.config.configmanager.model.entity;

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
@Table(name = "label_details")
@Data
public class LabelDetailsEntity {

	@Column(name = "label_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelId;

	@Column(name = "app_id")
	private Long appId;

	@Column(name = "label_display_name")
	private String labelDisplayName;

	@Column(name = "label_name")
	private String labelName;

	@Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private ZonedDateTime addedDate;

}
