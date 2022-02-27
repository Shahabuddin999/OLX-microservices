package com.olx.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNameAlreadyExistsException extends RuntimeException{
	private String message;

	public UserNameAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	public UserNameAlreadyExistsException() {
		super();
	}

	@Override
	public String toString() {
		return "UserNameAlreadyExistsException Error :" + message;
	}
	
}
