package com.zensar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	public TestController() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping("/")
	public String getData() {
		return "Shahabuddin Ansari";
	}
}
