/**
 * 
 */
package com.cloud.config.configmanager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author namadipta
 *
 */
@Entity
@Table(name = "user_access")
@Data
public class UserAccessEntity {

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "email")
	private String email;

	@Column(name = "view_access")
	private Boolean viewAccess;

	@Column(name = "admin")
	private Boolean admin;
}
