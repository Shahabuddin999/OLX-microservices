package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Category DTO")
public class CategoryDto {
	@ApiModelProperty(value="Category Identifier")
	private int id;
	@ApiModelProperty(value="Category Name")
	private String name;
	@ApiModelProperty(value="Category Description")
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryDto(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public CategoryDto() {
		super();
	}
	@Override
	public String toString() {
		return "AdvertiseStatus [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
