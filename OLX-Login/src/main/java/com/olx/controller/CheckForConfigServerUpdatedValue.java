package com.olx.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope 
public class CheckForConfigServerUpdatedValue {

	@Value("${spring.datasource.url}")
	private String dbURL;
	
	@GetMapping(value="read-property")
	public String getBDURL(){
		return "dbURL : "+this.dbURL;
	}
}
