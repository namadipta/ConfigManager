/**
 * 
 */
package com.cloud.config.configmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.config.configmanager.model.entity.UserAccessEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface UserAccessRepo extends JpaRepository<UserAccessEntity, Long> {

	/**
	 * @return
	 */
	UserAccessEntity findByName(String name);

}
