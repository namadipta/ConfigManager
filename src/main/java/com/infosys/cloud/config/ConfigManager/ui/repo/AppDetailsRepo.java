/**
 * 
 */
package com.infosys.cloud.config.configmanager.ui.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.cloud.config.ConfigManager.ui.model.entity.AppDetailsEntity;

/**
 * @author namadipta
 *
 */
@Repository
public interface AppDetailsRepo extends JpaRepository<AppDetailsEntity, Long> {

}