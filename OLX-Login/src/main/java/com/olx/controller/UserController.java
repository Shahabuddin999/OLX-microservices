package com.olx.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.UserDto;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.exception.InvalidUserNameOrPasswordException;
import com.olx.security.JwtUtil;
import com.olx.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/olxuser")
@CrossOrigin(origins = "*")
public class UserController {

		@Autowired
		UserService userService;
		
		@Autowired
		JwtUtil jwtUtil;
		
		@Autowired
		UserDetailsService userDetailsService;
		
		@Autowired
		AuthenticationManager authenticationManager;
		
		@PostMapping(value="/user/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		@ApiOperation(value="Authenticate User over here", notes = "Authenticate User over here and return response to the client") // This @ApiOperation and @ApiParam is belonging to swagger
		public ResponseEntity<String> authenticate(@ApiParam(value="Need to send User DTO to validate", name="userAuthentication", required = true) @RequestBody UserDto userDto) throws Exception{
			try {
				// Calling manually because not hitting from Login page provided by spring framework. here it is calling loadUserByUsername(String)
				Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
				System.out.println("Auth : " + auth);
			}catch(Exception e) {
				throw new InvalidUserNameOrPasswordException("You have intered Invalid UserName or Password");
			}
			String authTocken = jwtUtil.generateToken(userDto.getUsername());
			return new ResponseEntity<String>(authTocken, HttpStatus.OK);
		}
		
		@DeleteMapping(value="/user/logout")
		@ApiOperation(value="Logout user over here", notes = "Logout user over here and return response to the client") // This @ApiOperation and @ApiParam is belonging to swagger
		public ResponseEntity<Boolean> userLogout(@ApiParam(value="Pass Auth Tocken over here", name="Authorization", required = true) @RequestHeader("Authorization") String authorization) {
			return userService.userLogout(authorization);
		}
		
		@PostMapping(value="/user", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		@ApiOperation(value="Create user over here to create User", notes = "Create user over here and return response to the client") // This @ApiOperation and @ApiParam is belonging to swagger
		public ResponseEntity<UserDto> createUser(@ApiParam(value="Need to send User's DTO", name="user", required = true) @RequestBody UserDto user) {
			ResponseEntity<UserDto> response = userService.createUser(user);
			return response;
		}
		
		@GetMapping(value="/user", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		@ApiOperation(value="Get user over here", notes = "Get user over here and return response to the client") // This @ApiOperation and @ApiParam is belonging to swagger
		public ResponseEntity<UserDto> getUser(@ApiParam(value="Need to pass User's Auth Tocken", name="Authorization", required = true) @RequestHeader("Authorization") String authorization) {
			ResponseEntity<UserDto> user = null;
			if(Boolean.TRUE.equals(validateUserTocken(authorization).getBody())) {
				String userName = getUsername(authorization).getBody();
				user = userService.getUser(userName);
			}
			return user;
		}
		
		@GetMapping(value="/token/validate", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		@ApiOperation(value="Validate user over here...", notes = "Validate user over here and return response to the client___") // This @ApiOperation and @ApiParam is belonging to swagger
		public ResponseEntity<Boolean> validateUserTocken(@ApiParam(value="Need to send User Auth Tocken", name ="Authorization" ,required = true) @RequestHeader("Authorization") String authorization) {
			//ResponseEntity<Boolean> isValid = userService.validateTocken(authorization);
			return userService.validateTocken(authorization);
		}
		
		@GetMapping(value="/user/getUsername", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		@ApiOperation(value="Get UserName", notes = "Get UserName by AuthTocken") // This @ApiOperation and @ApiParam is belonging to swagger
		public ResponseEntity<String> getUsername(@ApiParam(value="Need to send User Auth Tocken", name ="Authorization" ,required = true) @RequestHeader("Authorization") String authorization) {
			String userName = "";
			try {
			String actualTocken = authorization.split(" ")[1];
			userName = jwtUtil.extractUsername(actualTocken);
			}  catch (Exception e) {
				throw new InvalidAuthTokenException("You have intered Invalid Auth Tocken");
			}
			return new ResponseEntity<String>(userName,HttpStatus.FOUND);
		}
			
		@GetMapping(value="/testing", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public ResponseEntity<Boolean> testing(@RequestHeader("authTocken") String authTocken) {
			System.out.println("hello spring");
			return userService.validateTocken(authTocken);
		}
}
