package com.olx.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.olx.dto.AdvertiseStatusDto;
import com.olx.dto.CategoryDto;

public interface MasterService {
	ResponseEntity<List<CategoryDto>> getAdvertise();
	ResponseEntity<List<AdvertiseStatusDto>> getStatus();
	ResponseEntity<CategoryDto> getCategoryById(Integer categoryId);
	ResponseEntity<AdvertiseStatusDto> getAdvertiseStatusDto(Integer categoryId);
}
