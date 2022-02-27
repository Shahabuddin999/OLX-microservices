package com.olx.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blacklisted_tokens")
public class BlackListedToken {

	@Id
	private int id;
	private String token;
	private LocalDateTime logoutdate;
	private LocalDateTime expirydate;
	public BlackListedToken(){
		
	}
	public BlackListedToken(int id, String token, LocalDateTime logoutdate, LocalDateTime expirydate) {
		super();
		this.id = id;
		this.token = token;
		this.logoutdate = logoutdate;
		this.expirydate = expirydate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getLogoutdate() {
		return logoutdate;
	}

	public void setLogoutdate(LocalDateTime logoutdate) {
		this.logoutdate = logoutdate;
	}

	public LocalDateTime getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(LocalDateTime expirydate) {
		this.expirydate = expirydate;
	}
}