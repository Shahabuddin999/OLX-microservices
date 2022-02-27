package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="User DTO")
public class UserDto {
	@ApiModelProperty(value="User Identifier")
	private int id;
	@ApiModelProperty(value="User User Name")
	private String username;
	@ApiModelProperty(value="User Password")
	private String password;
	@ApiModelProperty(value="User Roles")
	private String roles;
	@ApiModelProperty(value="User Active")
	private boolean active;
	@ApiModelProperty(value="User First Name")
	private String firstName;
	@ApiModelProperty(value="User Last Name")
	private String lastName;
	@ApiModelProperty(value="User Email")
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserDto(int id, String username, String password, String roles, boolean active, String firstName,
			String lastName, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.active = active;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public UserDto() {
		super();
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ ", active=" + active + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		UserDto userDto = (UserDto) obj;
		if (this.username == null) {
			return false;
		}
		if (this.username.equals(userDto.username)) {
			return true;
		}
		return false;
	}
}
