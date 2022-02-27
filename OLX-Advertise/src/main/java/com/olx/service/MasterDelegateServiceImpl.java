package com.olx.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.olx.dto.AdvertiseStatusDto;
import com.olx.dto.CategoryDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
public class MasterDelegateServiceImpl implements MasterDelegateService{

	@Autowired
	RestTemplate restTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@CircuitBreaker(name="GET-ALL-CATEGORY-CIRCUIT-BREAKER", fallbackMethod = "checkToGetAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		ResponseEntity<List<CategoryDto>> responceEntity1= (ResponseEntity) restTemplate.getForEntity("http://olx-gateway/advertiseApp/advertise/category",List.class);
		//ResponseEntity<List<CategoryDto>> responceEntity1= (ResponseEntity) restTemplate.getForEntity("http://localhost:8081/advertiseApp/advertise/category",List.class);
		//ResponseEntity<List<CategoryDto>> responceEntity1= (ResponseEntity) restTemplate.getForEntity("http://localhost:8081/advertiseApp/advertise/category",List.class);
		List<CategoryDto> list = responceEntity1.getBody();
		System.out.println(responceEntity1.getBody());
		System.out.println(responceEntity1);
		return new ResponseEntity<List<CategoryDto>>(list,responceEntity1.getStatusCode());
	}
	public ResponseEntity<List<CategoryDto>> checkToGetAllCategory(Exception e) {
		System.out.println("inside checkToGetAllCategory: "+e.getMessage());
		return new ResponseEntity(new CategoryDto(),HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@CircuitBreaker(name="GET-CATEGORY-BY-ID-CIRCUIT-BREAKER", fallbackMethod = "checkToGetCategoryById")
	public ResponseEntity<CategoryDto> getCategoryById(Integer categoryId) {
		ResponseEntity<CategoryDto> categoryDto = restTemplate.getForEntity("http://olx-gateway/advertiseApp/advertise/category/"+categoryId,CategoryDto.class);
		//ResponseEntity<CategoryDto> categoryDto = restTemplate.getForEntity("http://localhost:8081/advertiseApp/advertise/category/"+categoryId,CategoryDto.class);
		//ResponseEntity<CategoryDto> categoryDto = restTemplate.getForEntity("http://localhost:8081/advertiseApp/advertise/category/"+categoryId,CategoryDto.class);
		System.out.println(categoryDto.getBody());
		return new ResponseEntity(categoryDto.getBody(),categoryDto.getStatusCode());
	}
	public ResponseEntity<CategoryDto> checkToGetCategoryById(Integer categoryId, Exception e) {
		System.out.println("inside checkToGetCategoryById: "+e.getMessage());
		return new ResponseEntity(new CategoryDto(),HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@CircuitBreaker(name="GET-ADVERTISE-STATUS-CIRCUIT-BREAKER", fallbackMethod = "checkToGetAdvertiseStatusDto")
	public ResponseEntity<AdvertiseStatusDto> getAdvertiseStatusDto(Integer advertiseStatusId) {
		ResponseEntity<AdvertiseStatusDto> advertiseStatusDto = restTemplate.getForEntity("http://olx-gateway/advertiseApp/advertise/advertiseStatus/"+advertiseStatusId,AdvertiseStatusDto.class);
		//ResponseEntity<AdvertiseStatusDto> advertiseStatusDto = restTemplate.getForEntity("http://localhost:8081/advertiseApp/advertise/advertiseStatus/"+advertiseStatusId,AdvertiseStatusDto.class);
		//ResponseEntity<AdvertiseStatusDto> advertiseStatusDto = restTemplate.getForEntity("http://localhost:8081/advertiseApp/advertise/advertiseStatus/"+advertiseStatusId,AdvertiseStatusDto.class);
		System.out.println(advertiseStatusDto.getBody());
		return new ResponseEntity(advertiseStatusDto.getBody(),advertiseStatusDto.getStatusCode());
	}

	public ResponseEntity<AdvertiseStatusDto> checkToGetAdvertiseStatusDto(Integer advertiseStatusId, Exception e) {
		System.out.println("inside checkToGetAdvertiseStatusDto: "+e.getMessage());
		return new ResponseEntity(new AdvertiseStatusDto(),HttpStatus.OK);
	}	
	@Override
	@CircuitBreaker(name="VALIDATE-CIRCUIT-BREAKER", fallbackMethod = "fallbackIsValidUser")
	public ResponseEntity<Boolean> isValivUser(String authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorization);
		HttpEntity entity = new HttpEntity(headers);
		//ResponseEntity<Boolean> result = this.restTemplate.exchange("http://localhost:8080/olxuser/token/validate",HttpMethod.GET, entity, Boolean.class);
		//ResponseEntity<Boolean> result = this.restTemplate.exchange("http://login-service/olxuser/token/validate",HttpMethod.GET, entity, Boolean.class);
		ResponseEntity<Boolean> result = this.restTemplate.exchange("http://olx-gateway/olxuser/token/validate",HttpMethod.GET, entity, Boolean.class);
		return result;
	}
	
	public ResponseEntity<Boolean> fallbackIsValidUser(String authorization, Exception e){
		System.out.println("inside fallbackIsValidUser: "+e.getMessage());
		return new ResponseEntity(false,HttpStatus.OK);
		
	}
	
	@Override
	@CircuitBreaker(name="GET-USERNAME-CIRCUIT-BREAKER", fallbackMethod = "checkToGetUserName")
	public ResponseEntity<String> getUserName(String authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorization);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> result = this.restTemplate.exchange("http://olx-gateway/olxuser/user/getUsername",HttpMethod.GET, entity, String.class);
		return result;
	}
	
	public ResponseEntity<String> checkToGetUserName(String authorization, Exception e) {
		System.out.println("inside checkToGetUserName: "+e.getMessage());
		return new ResponseEntity(new String(),HttpStatus.OK);
	}
}
