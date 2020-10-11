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
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * @author namadipta
 *
 */
@Entity
@Table(name = "prop_details")
@Data
public class PropDetailsEntity {

	@Column(name = "prop_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long propId;

	@Column(name = "prop_version")
	private Long propVersion;

	@Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private ZonedDateTime addedDate;

	@Column(name = "modified_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private ZonedDateTime modifiedDate;

	@Column(name = "label_id")
	private Long labelId;

	@Column(name = "prof_id")
	private Long profId;

	@Column(name = "mod_id")
	private Long modId;

	@Column(name = "added_by")
	private String addedBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Lob
	@Column(name = "prop_data")
	private String propData;
}
