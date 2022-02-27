package com.olx.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserNameOrPasswordException extends RuntimeException{
	private String message;

	public InvalidUserNameOrPasswordException(String message) {
		super();
		this.message = message;
	}

	public InvalidUserNameOrPasswordException() {
		super();
	}

	@Override
	public String toString() {
		return "InvalidUserNameOrPasswordException Error :" + message;
	}
	
}
