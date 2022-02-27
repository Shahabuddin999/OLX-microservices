package com.olx.service;
 
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.olx.dto.AdvertiseDto;
import com.olx.dto.AdvertiseStatusDto;
import com.olx.dto.CategoryDto;

public interface MasterDelegateService {
	ResponseEntity<List<CategoryDto>> getAllCategory();
	ResponseEntity<CategoryDto> getCategoryById(Integer categoryId);
	ResponseEntity<AdvertiseStatusDto> getAdvertiseStatusDto(Integer categoryId);
	ResponseEntity<Boolean> isValivUser(String authorization);
	ResponseEntity<String> getUserName(String authorization);
}
