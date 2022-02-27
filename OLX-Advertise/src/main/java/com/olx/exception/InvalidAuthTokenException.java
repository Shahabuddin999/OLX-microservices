package com.olx.exception;
 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthTokenException extends RuntimeException{
	private String message;

	public InvalidAuthTokenException(String message) {
		super();
		this.message = message;
	}

	public InvalidAuthTokenException() {
		super();
	}

	@Override
	public String toString() {
		return "InvalidAuthTokenException Error :" + message;
	}
}
