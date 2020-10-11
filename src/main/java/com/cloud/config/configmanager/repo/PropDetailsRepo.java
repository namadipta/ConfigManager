/**
 * 
 */
package com.cloud.config.configmanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.config.configmanager.model.entity.PropDetailsEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface PropDetailsRepo extends JpaRepository<PropDetailsEntity, Long> {

	/**
	 * @param appId
	 * @param modId
	 * @param labelId
	 * @param profId
	 * @return
	 */
	PropDetailsEntity findFirstByModIdAndLabelIdAndProfIdOrderByPropVersionDesc( Long modId,
			Long labelId, Long profId);

	/**
	 * @param appId
	 * @param modId
	 * @param labelId
	 * @param profId
	 * @param propVersion
	 * @return
	 */
	PropDetailsEntity findFirstByModIdAndLabelIdAndProfIdAndPropVersion(Long modId, Long labelId,
			Long profId, Long propVersion);

	/**
	 * @param parseLong
	 * @param parseLong2
	 * @param parseLong3
	 * @param parseLong4
	 * @return
	 */
	List<PropDetailsEntity> findAllByModIdAndLabelIdAndProfIdOrderByPropVersionDesc(
			long selectedModule, long selectedProfile, long selectedLabel);

}
