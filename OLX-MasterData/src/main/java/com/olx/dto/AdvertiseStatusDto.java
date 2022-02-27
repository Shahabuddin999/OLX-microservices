package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Advertise DTO")
public class AdvertiseStatusDto {
	@ApiModelProperty(value="Advertise Identifier")
	private int id;
	@ApiModelProperty(value="Advertise Status")
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AdvertiseStatusDto(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public AdvertiseStatusDto() {
		super();
	}
	@Override
	public String toString() {
		return "AdvertiseStatus [id=" + id + ", status=" + status + "]";
	}
}
