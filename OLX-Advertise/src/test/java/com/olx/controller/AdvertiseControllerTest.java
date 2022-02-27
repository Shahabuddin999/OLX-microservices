package com.olx.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.AdvertiseDto;
import com.olx.entity.AdvertiseEntity;
import com.olx.service.AdvertiseService;

@WebMvcTest(AdvertiseController.class)
public class AdvertiseControllerTest {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AdvertiseService advertiseService;

	@Test
	public void testSearchAdvertisesByFilterCriteria() throws Exception {
		List<AdvertiseDto> advertises = new ArrayList<AdvertiseDto>();
		advertises.add(new AdvertiseDto());
		advertises.add(new AdvertiseDto());
		when(this.advertiseService.searchAdvertisesByFilterCriteria(null, 0, null, null, null, null, null, null, 0, 0))
				.thenReturn(advertises);
				MvcResult mvcResult = mockMvc.perform(get("/advertiseMasterApp/advertise/searchFields/filtercriteria")
				.accept("application/json")
				.param("category", "0")
				.param("startIndex", "0")
				.param("records", "0"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		System.out.println("response: " + response);
		assertEquals(response.contains("title"), true);
	}

	@Test
	void testCreateAdvertise_success() throws JsonProcessingException, Exception {
		AdvertiseDto advertiseDto = new AdvertiseDto();
		advertiseDto.setTitle("sofa for sale data");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer D78U");
		AdvertiseDto expectedResponse = new AdvertiseDto();
		expectedResponse.setTitle("shahabuddin for sale");
		String auth = headers.getFirst("Authorization");
		when(this.advertiseService.createAdvertise(advertiseDto, auth)).thenReturn(new ResponseEntity<AdvertiseDto>(expectedResponse, HttpStatus.CREATED));

//		mockMvc.perform(post("/advertiseMasterApp/advertise").contentType("application/json").accept("application/json")
//				.content(objectMapper.writeValueAsString(advertiseDto))
//				.headers(headers))
//				.andExpect(status().isCreated()).andExpect(content().string(containsString("szaafa"))).andReturn();
		
		MvcResult mvcResult  = mockMvc.perform(post("/advertiseMasterApp/advertise").contentType("application/json").accept("application/json")
				.content(objectMapper.writeValueAsString(advertiseDto))
				.headers(headers))
				.andExpect(status().isCreated()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(result.contains("shahabuddin"), true);
	}
	
	@Test
	void testUpdateAdvertise_success() throws JsonProcessingException, Exception {
		AdvertiseDto advertiseDto = new AdvertiseDto();
		Integer id = 1;
		advertiseDto.setTitle("Shahabuddin Ansari");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer D78U");
		AdvertiseDto expectedResponse = new AdvertiseDto();
		expectedResponse.setTitle("shahabuddin Ahmad sale");
		String auth = headers.getFirst("Authorization");
		when(this.advertiseService.updateAdvertise(advertiseDto, auth, id)).thenReturn(new ResponseEntity<AdvertiseDto>(expectedResponse,HttpStatus.ACCEPTED));
		
		MvcResult mvcResult  = mockMvc.perform(put("/advertiseMasterApp/advertise/"+id)
				.contentType("application/json")
				.accept("application/json")
				.content(objectMapper.writeValueAsString(advertiseDto))
				.headers(headers))
				.andExpect(status().isAccepted()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(true, result.contains("shahabuddin"));
	}
	
	@Test
	void testGetAdvertiseByLoggedInUser_success() throws JsonProcessingException, Exception {
		AdvertiseDto advertiseDto = new AdvertiseDto();
		advertiseDto.setTitle("Shahabuddin Ansari");
		
		List<AdvertiseDto> list = new ArrayList<>();
		AdvertiseDto expectedResponse = new AdvertiseDto();
		expectedResponse.setTitle("shahabuddin Ahmad sale");
		list.add(expectedResponse);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer D78U");
		
		String auth = headers.getFirst("Authorization");
		when(this.advertiseService.getAdvertiseByLoggedInUser(auth)).thenReturn(new ResponseEntity<List<AdvertiseDto>>(list,HttpStatus.FOUND));
		
		MvcResult mvcResult  = mockMvc.perform(get("/advertiseMasterApp/user/advertise")
				.contentType("application/json")
				.accept("application/json")
				.headers(headers))
				.andExpect(status().isFound()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(true, result.contains("shahabuddin"));
	}
	
	@Test
	void testGetAdvertiseByLoggedInUserAndId_success() throws JsonProcessingException, Exception {
		
		Integer id = 1;
		AdvertiseDto expectedResponse = new AdvertiseDto();
		expectedResponse.setTitle("shahabuddin Ahmad sale");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer D78U");
		
		String auth = headers.getFirst("Authorization");
		when(this.advertiseService.getAdvertiseByLoggedInUserAndId(auth,id)).thenReturn(new ResponseEntity<AdvertiseDto>(expectedResponse,HttpStatus.FOUND));
		
		MvcResult mvcResult  = mockMvc.perform(get("/advertiseMasterApp/user/advertise/"+id)
				.contentType("application/json")
				.accept("application/json")
				.headers(headers))
				.andExpect(status().isFound()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(true, result.contains("shahabuddin"));
	}
	
	
	@Test
	void testDeleteAdvertiseByLoggedInUserAndId_success() throws JsonProcessingException, Exception {
		
		Integer id = 1;
		AdvertiseDto expectedResponse = new AdvertiseDto();
		expectedResponse.setTitle("shahabuddin Ahmad sale");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer D78U");
		
		String auth = headers.getFirst("Authorization");
		when(this.advertiseService.deleteAdvertiseByLoggedInUserAndId(auth,id)).thenReturn(new ResponseEntity<Boolean>(true,HttpStatus.ACCEPTED));
		
		MvcResult mvcResult  = mockMvc.perform(delete("/advertiseMasterApp/user/advertise/"+id)
				.contentType("application/json")
				.accept("application/json")
				.headers(headers))
				.andExpect(status().isAccepted()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(true, result.contains("true"));
	}
	
	@Test
	void testGetAdvertiseById_success() throws JsonProcessingException, Exception {
		
		Integer id = 1;
		AdvertiseDto expectedResponse = new AdvertiseDto();
		expectedResponse.setTitle("shahabuddin Ahmad sale");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer D78U");
		
		String auth = headers.getFirst("Authorization");
		when(this.advertiseService.getAdvertiseById(auth,id)).thenReturn(new ResponseEntity<AdvertiseDto>(expectedResponse,HttpStatus.FOUND));
		
		MvcResult mvcResult  = mockMvc.perform(get("/advertiseMasterApp/advertise/"+id)
				.contentType("application/json")
				.accept("application/json")
				.headers(headers))
				.andExpect(status().isFound()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(true, result.contains("shahabuddin"));
	}
	
	@Test
	void tesGetAdvertiseByText_success() throws JsonProcessingException, Exception {
		List<AdvertiseDto> list = new ArrayList<>();
		AdvertiseDto advertiseDto = new AdvertiseDto();
		advertiseDto.setTitle("shahabuddin Ansari");
		AdvertiseDto expectedResponse = new AdvertiseDto();
		expectedResponse.setTitle("shahabuddin Ahmad sale");
		list.add(expectedResponse);
		
		String searchText = "abc";
		
		
		when(this.advertiseService.getAdvertiseByText(searchText)).thenReturn(new ResponseEntity<List<AdvertiseDto>>(list,HttpStatus.FOUND));
		
		MvcResult mvcResult  = mockMvc.perform(get("/advertiseMasterApp/advertise/search/text")
				.contentType("application/json")
				.accept("application/json")
				.param("searchText", searchText))
				.andExpect(status().isFound()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(true, result.contains("Ahmad"));
	}
}
