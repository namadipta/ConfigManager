/**
 * 
 */
package com.cloud.config.configmanager.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.config.configmanager.model.service.PropertyPojo;

/**
 * @author namadipta
 *
 */
@Service
public class FileUploadService {

	/**
	 * Upload File
	 * 
	 * @param file
	 * @return
	 */
	public List<PropertyPojo> uploadFile(MultipartFile file) {
		byte[] bytes;
		try {
			bytes = file.getBytes();
			List<String> kayValue = Arrays.asList(StringUtils.split(new String(bytes), "\n"));
			if (!CollectionUtils.isEmpty(kayValue)) {
				kayValue = kayValue.stream().filter(i -> !StringUtils.startsWith(i, "#")).collect(Collectors.toList());
			}
			List<PropertyPojo> propertyList = new ArrayList<>();
			kayValue.stream().forEach(j -> {
				try {
					List<String> pair = Arrays.asList(StringUtils.split(j, "="));
					PropertyPojo propertyPojo = new PropertyPojo();
					propertyPojo.setId(pair.get(0));
					propertyPojo.setText(pair.get(1));
					propertyList.add(propertyPojo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			System.out.println("propertyList" + propertyList);
			return propertyList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
