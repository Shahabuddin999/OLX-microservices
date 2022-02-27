package com.olx.exception;
 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusNotFoundException extends RuntimeException{
	private String message;

	public StatusNotFoundException(String message) {
		super();
		this.message = message;
	}

	public StatusNotFoundException() {
		super();
	}

	@Override
	public String toString() {
		return "StatusNotFoundException Error :" + message;
	}
}
