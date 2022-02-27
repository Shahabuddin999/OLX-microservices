package com.olx.service;

import org.springframework.http.ResponseEntity;

import com.olx.dto.UserDto;

public interface UserService {
	ResponseEntity<String> authenticate(UserDto userAuthentication);
	ResponseEntity<Boolean> userLogout(String authTocken);
	ResponseEntity<UserDto> getUser(String authTocken);
	ResponseEntity<UserDto> createUser(UserDto user);
	ResponseEntity<Boolean> validateTocken(String authTocken);
}
