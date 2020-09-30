/**
 * 
 */
package com.cloud.config.configmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.config.configmanager.model.entity.AppDetailsEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface AppDetailsRepo extends JpaRepository<AppDetailsEntity, Long> {

}
