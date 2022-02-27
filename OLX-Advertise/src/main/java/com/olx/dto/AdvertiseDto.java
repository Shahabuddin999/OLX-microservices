package com.olx.dto;
 
import java.sql.Blob;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Advertise DTO")
public class AdvertiseDto {
	@ApiModelProperty(value = "Advertise Identifier")
	private int id;
	@ApiModelProperty(value = "Advertise Title")
	private String title;
	@ApiModelProperty(value = "Advertise Category")
	private int category;
	@ApiModelProperty(value = "Advertise Status")
	private int status;
	@ApiModelProperty(value = "Advertise Price")
	private double price;
	@ApiModelProperty(value = "Advertise Description")
	private String description;
	@ApiModelProperty(value = "Advertise Photo")
	private Blob photo;
	@ApiModelProperty(value = "Advertise Created date")
	private LocalDate createdDate;
	@ApiModelProperty(value = "Advertise Modified date")
	private LocalDate modifiedDate;
	@ApiModelProperty(value = "Advertise Active")
	private boolean active;
	@ApiModelProperty(value = "Advertise Posted By")
	private String postedBy;
	@ApiModelProperty(value = "Advertise User Name")
	private String userName;
	@ApiModelProperty(value = "Advertise Status Name")
	private String statusName;
	@ApiModelProperty(value = "Advertise Category Name")
	private String categoryName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public AdvertiseDto(int id, String title, int category, int status, double price, String description, Blob photo,
			LocalDate createdDate, LocalDate modifiedDate, boolean active, String postedBy, String userName,
			String statusName, String categoryName) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.status = status;
		this.price = price;
		this.description = description;
		this.photo = photo;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.active = active;
		this.postedBy = postedBy;
		this.userName = userName;
		this.statusName = statusName;
		this.categoryName = categoryName;
	}

	public AdvertiseDto() {
		super();
	}

	@Override
	public String toString() {
		return "AdvertiseDto [id=" + id + ", title=" + title + ", category=" + category + ", status=" + status
				+ ", price=" + price + ", description=" + description + ", photo=" + photo + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", active=" + active + ", postedBy=" + postedBy
				+ ", userName=" + userName + ", statusName=" + statusName + ", categoryName=" + categoryName + "]";
	}

	@Override
	public boolean equals(Object obj) {
		AdvertiseDto advertiseDTO = (AdvertiseDto) obj;
		if (this.title == null) {
			return false;
		}
		if (this.title.equals(advertiseDTO.title)) {
			return true;
		}
		return false;
	}

}
