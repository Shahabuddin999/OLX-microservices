package com.olx.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.AdvertiseStatusDto;
import com.olx.dto.CategoryDto;
import com.olx.service.MasterService;

@WebMvcTest(MasterController.class)
public class MasterControllerTest {

	@MockBean
	MasterService masterService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void getCategoryTest_success() throws JsonProcessingException, Exception {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName("Shahab");
		List<CategoryDto> list = new ArrayList<>();
		list.add(categoryDto);
		ResponseEntity<List<CategoryDto>> expectedResponse = new ResponseEntity<List<CategoryDto>>(list,HttpStatus.FOUND);
		when(this.masterService.getAdvertise()).thenReturn(expectedResponse);
		mockMvc.perform(get("/advertiseApp/advertise/category")
				.contentType("application/json")
			)
			.andExpect(status().isFound())
			.andExpect(content().string(containsString("Shahab")))
			.andReturn();
	}
	
	@Test
	void getStatusTest_success() throws JsonProcessingException, Exception {
		AdvertiseStatusDto advertiseStatusDto = new AdvertiseStatusDto();
		advertiseStatusDto.setStatus("Shahab status");
		List<AdvertiseStatusDto> list = new ArrayList<>();
		list.add(advertiseStatusDto);
		ResponseEntity<List<AdvertiseStatusDto>> expectedResponse = new ResponseEntity<List<AdvertiseStatusDto>>(list,HttpStatus.FOUND);
		when(this.masterService.getStatus()).thenReturn(expectedResponse);
		mockMvc.perform(get("/advertiseApp/advertise/status")
				.contentType("application/json")
			)
			.andExpect(status().isFound())
			.andExpect(content().string(containsString("Shahab")))
			.andReturn();
	}
	
	///////
	
	@Test
	void getCategoryByIdTest_success() throws JsonProcessingException, Exception {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName("Shahab");
		Integer id = 1;
		ResponseEntity<CategoryDto> expectedResponse = new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.FOUND);
		when(this.masterService.getCategoryById(id)).thenReturn(expectedResponse);
		mockMvc.perform(get("/advertiseApp/advertise/category/"+id)
				.contentType("application/json")
			)
			.andExpect(status().isFound())
			.andExpect(content().string(containsString("Shahab")))
			.andReturn();
	}
	
	@Test
	void getAdvertiseStatusByIdTest_success() throws JsonProcessingException, Exception {
		AdvertiseStatusDto advertiseStatusDto = new AdvertiseStatusDto();
		advertiseStatusDto.setStatus("Shahab status");
		Integer id = 1;
		ResponseEntity<AdvertiseStatusDto> expectedResponse = new ResponseEntity<AdvertiseStatusDto>(advertiseStatusDto,HttpStatus.FOUND);
		when(this.masterService.getAdvertiseStatusDto(id)).thenReturn(expectedResponse);
		mockMvc.perform(get("/advertiseApp/advertise/advertiseStatus/"+id)
				.contentType("application/json")
			)
			.andExpect(status().isFound())
			.andExpect(content().string(containsString("Shahab")))
			.andReturn();
	}
}
