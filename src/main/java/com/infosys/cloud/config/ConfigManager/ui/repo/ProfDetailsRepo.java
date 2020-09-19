/**
 * 
 */
package com.infosys.cloud.config.ConfigManager.ui.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.cloud.config.ConfigManager.ui.model.entity.ProfDetailsEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface ProfDetailsRepo extends JpaRepository<ProfDetailsEntity, Long> {

	/**
	 * @param appId
	 * @return
	 */
	List<ProfDetailsEntity> findByAppId(Long appId);

}
