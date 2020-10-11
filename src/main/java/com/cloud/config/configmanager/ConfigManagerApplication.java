package com.cloud.config.configmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author namadipta
 *
 */
@SpringBootApplication
@ComponentScan({ "com.cloud" })
public class ConfigManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigManagerApplication.class, args);
	}

}
