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
@Table(name = "profile_details")
@Data
public class ProfDetailsEntity {

	@Column(name = "prof_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profId;

	@Column(name = "app_id")
	private Long appId;

	@Column(name = "prof_display_name")
	private String profDisplayName;

	@Column(name = "prof_name")
	private String profName;

	@Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private ZonedDateTime addedDate;

}
