/**
 * 
 */
package com.infosys.cloud.config.configmanager.ui.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.cloud.config.ConfigManager.ui.model.entity.EnvDetailsEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface EnvDetailsRepo extends JpaRepository<EnvDetailsEntity, Long> {

	/**
	 * @param appId
	 * @return
	 */
	List<EnvDetailsEntity> findByAppId(Long appId);

}
