package com.olx.exception;
 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAdvertiseIdException extends RuntimeException{
	private String message;

	public InvalidAdvertiseIdException(String message) {
		super();
		this.message = message;
	}

	public InvalidAdvertiseIdException() {
		super();
	}

	@Override
	public String toString() {
		return "InvalidAdvertiseIdException Error :" + message;
	}
	
}
