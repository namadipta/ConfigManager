/**
 * 
 */
package com.cloud.config.configmanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.config.configmanager.model.entity.ModuleDetailsEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface ModuleDetailsRepo extends JpaRepository<ModuleDetailsEntity, Long> {

	/**
	 * @param appId
	 * @return
	 */
	List<ModuleDetailsEntity> findByAppId(Long appId);

}
