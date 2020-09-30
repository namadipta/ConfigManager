/**
 * 
 */
package com.cloud.config.configmanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.config.configmanager.model.entity.LabelDetailsEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface LabelDetailsRepo extends JpaRepository<LabelDetailsEntity, Long> {

	/**
	 * @param appId
	 * @return
	 */
	List<LabelDetailsEntity> findByAppId(Long appId);

}
