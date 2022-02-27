package com.olx.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.UserDto;
import com.olx.security.JwtUtil;
import com.olx.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	JwtUtil jwtUtil;
	
	@MockBean
	UserService userService;
	
	@MockBean
	UserDetailsService userDetailsService;
	
	@MockBean
	AuthenticationManager authenticationManager;
	
	@Test
	void testAuthenticate_success() throws JsonProcessingException, Exception {
		UserDto userDto = new UserDto();
		userDto.setUsername("shahab");
		userDto.setPassword("alld");
		String expectedResponse = "D7F8";
		
		when(jwtUtil.generateToken(userDto.getUsername())).thenReturn(expectedResponse);
		mockMvc.perform(post("/olxuser/user/authenticate")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(userDto))
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("F8")))
			.andReturn();
	}
	
	@Test
	void testCreateUser_success() throws JsonProcessingException, Exception {
		UserDto actualUser = new UserDto();
		actualUser.setUsername("shahab");
		actualUser.setPassword("alld");
		
		UserDto expectedUserDto = new UserDto();
		expectedUserDto.setUsername("shahabuddin");
		expectedUserDto.setPassword("Ansari");
		
		when(this.userService.createUser(actualUser)).thenReturn(new ResponseEntity<UserDto>(expectedUserDto,HttpStatus.CREATED));
		mockMvc.perform(post("/olxuser/user")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(actualUser))
			)
			.andExpect(status().isCreated())
			.andExpect(content().string(containsString("shahab")))
			.andReturn();
	}
	
	
	@Test
	void testGetUser_success() throws JsonProcessingException, Exception {
		UserDto expectedUserDto = new UserDto();
		expectedUserDto.setUsername("shahab");
		expectedUserDto.setPassword("alld");
		String tocken = "bearer ETA4";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", tocken);
		UserDetails user = new User(expectedUserDto.getUsername(),expectedUserDto.getPassword(),new ArrayList<GrantedAuthority>());
		when(this.userService.getUser(expectedUserDto.getUsername())).thenReturn(new ResponseEntity<UserDto>(expectedUserDto,HttpStatus.FOUND));
		when(jwtUtil.extractUsername(tocken.split(" ")[1])).thenReturn(expectedUserDto.getUsername());
		when(userDetailsService.loadUserByUsername(expectedUserDto.getUsername())).thenReturn(user);
		when(jwtUtil.validateToken(tocken.split(" ")[1], user)).thenReturn(true);
		mockMvc.perform(get("/olxuser/user")
				.accept("application/json")
				.headers(headers)
			)
			.andExpect(status().isFound())
			.andExpect(content().string(containsString("shahab")))
			.andReturn();
	}
	
	@Test
	void testvalidateTocken_success() throws JsonProcessingException, Exception {
		UserDto expectedUserDto = new UserDto();
		expectedUserDto.setUsername("shahab");
		expectedUserDto.setPassword("alld");
		String tocken = "bearer ETA4";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", tocken);
		UserDetails user = new User(expectedUserDto.getUsername(),expectedUserDto.getPassword(),new ArrayList<GrantedAuthority>());
		when(jwtUtil.extractUsername(tocken.split(" ")[1])).thenReturn(expectedUserDto.getUsername());
		when(userDetailsService.loadUserByUsername(expectedUserDto.getUsername())).thenReturn(user);
		when(jwtUtil.validateToken(tocken.split(" ")[1], user)).thenReturn(true);
		mockMvc.perform(get("/olxuser/token/validate")
				.contentType("application/json")
				.headers(headers)
			)
			.andExpect(status().isFound())
			.andExpect(content().string(containsString("true")))
			.andReturn();
	}
	
}
