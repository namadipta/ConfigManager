/**
 * 
 */
package com.cloud.config.configmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.config.configmanager.model.entity.ProfDetailsEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface ProfDetailsRepo extends JpaRepository<ProfDetailsEntity, Long> {

}
