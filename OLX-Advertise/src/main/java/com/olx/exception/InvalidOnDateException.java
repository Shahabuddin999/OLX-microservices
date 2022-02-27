package com.olx.exception;
 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOnDateException extends RuntimeException{
	private String message;

	public InvalidOnDateException(String message) {
		super();
		this.message = message;
	}

	public InvalidOnDateException() {
		super();
	}

	@Override
	public String toString() {
		return "InvalidOnDateException Error :" + message;
	}
}
