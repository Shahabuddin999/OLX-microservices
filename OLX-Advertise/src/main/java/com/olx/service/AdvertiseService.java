package com.olx.service;
 
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.olx.dto.AdvertiseDto;
import com.olx.dto.AdvertiseStatusDto;
import com.olx.dto.CategoryDto;

public interface AdvertiseService {
	ResponseEntity<AdvertiseDto>  createAdvertise(AdvertiseDto createAdvertise ,String authTocken);
	ResponseEntity<AdvertiseDto>  updateAdvertise(AdvertiseDto createAdvertise ,String authTocken,int advertiseId);
	ResponseEntity<List<AdvertiseDto>>  getAdvertiseByLoggedInUser(String authTocken);
	ResponseEntity<AdvertiseDto>  getAdvertiseByLoggedInUserAndId(String authTocken, int advertiseId);
	ResponseEntity<Boolean>  deleteAdvertiseByLoggedInUserAndId(String authTocken,int advertiseId);
	
	ResponseEntity<List<AdvertiseDto>>  getAdvertiseByCriteria(String searchText, int category,String postedBy,String dateCondition,LocalDate onDate,
			LocalDate fromDate,	LocalDate toDate,String sortBy,int startIndex,int records);
	
	ResponseEntity<List<AdvertiseDto>>  getAdvertiseByText(String searchText);
	ResponseEntity<AdvertiseDto>  getAdvertiseById(String authTocken, int advertiseId);
	ResponseEntity<List<CategoryDto>> getAllCategory();
	
	ResponseEntity<CategoryDto> getCategoryById(Integer categoryId);
	ResponseEntity<AdvertiseStatusDto> getAdvertiseStatusDto(Integer categoryId);
	List<AdvertiseDto> searchAdvertisesByFilterCriteria(String searchText, int categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records);
	AdvertiseDto createAdvertiseDummy(AdvertiseDto advertiseDto, String authorization);
}
