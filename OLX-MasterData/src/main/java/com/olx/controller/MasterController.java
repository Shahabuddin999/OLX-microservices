package com.olx.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseStatusDto;
import com.olx.dto.CategoryDto;
import com.olx.service.MasterService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/advertiseApp")
@CrossOrigin(origins = "*")
public class MasterController {
	
	@Autowired
	MasterService masterService;
	
	@GetMapping(value="/advertise/category", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read All Category over here", notes = "Read All Category over here and return response to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<List<CategoryDto>> getCategory() {
		ResponseEntity<List<CategoryDto>> response =  masterService.getAdvertise();
		return response;
	}
	
	@GetMapping(value="/advertise/status", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read All Category Status over here", notes = "Read All Category Status over here and return response to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<List<AdvertiseStatusDto>> getStatus() {
		ResponseEntity<List<AdvertiseStatusDto>> response = masterService.getStatus();
		return response;
	}
	
	@GetMapping(value="/advertise/category/{categoryId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read specific Category over here", notes = "Read specific Category over here and return response to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<CategoryDto> getCategoryById(@ApiParam(value="Need to Category Id", name = "categoryId", required = true)  @PathVariable("categoryId") Integer categoryId) {
		ResponseEntity<CategoryDto> response = masterService.getCategoryById(categoryId);
		return response;
	}
	
	@GetMapping(value="/advertise/advertiseStatus/{advertiseStatusId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read specific Category over here", notes = "Read specific Category over here and return response to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<AdvertiseStatusDto> getAdvertiseStatusById(@ApiParam(value="Need to pass advertiseStatus Id", name = "advertiseStatusId", required = true)  @PathVariable("advertiseStatusId") Integer advertiseStatusId) {
		ResponseEntity<AdvertiseStatusDto> response = masterService.getAdvertiseStatusDto(advertiseStatusId);
		return response;
	}
}
