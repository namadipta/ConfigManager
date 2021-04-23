CREATE DATABASE  IF NOT EXISTS `cloudconfig`;
USE `cloudconfig`;

CREATE TABLE IF NOT EXISTS `label_details` (
  `label_id` bigint NOT NULL AUTO_INCREMENT,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `label_name` text NOT NULL,
  `label_display_name` text NOT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `module_details` (
  `mod_id` bigint NOT NULL AUTO_INCREMENT,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mod_display_name` text NOT NULL,
  `mod_name` text NOT NULL,
  `added_by` text,
  PRIMARY KEY (`mod_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `profile_details` (
  `prof_id` bigint NOT NULL AUTO_INCREMENT,
  `prof_name` text,
  `prof_display_name` text,
  `added_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`prof_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `prop_details` (
  `prop_id` bigint NOT NULL AUTO_INCREMENT,
  `prop_version` bigint NOT NULL,
  `added_date` timestamp NULL DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `mod_id` bigint NOT NULL,
  `label_id` bigint NOT NULL,
  `prof_id` bigint NOT NULL,
  `added_by` text,
  `modified_by` text,
  `prop_data` longtext NOT NULL,
  PRIMARY KEY (`prop_id`)
) ENGINE=MyISAM AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `user_access` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `admin` varchar(45) DEFAULT NULL,
  `view_access` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
