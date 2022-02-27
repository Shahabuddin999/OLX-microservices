package com.olx.exception;
 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDateRangeException extends RuntimeException{
	private String message;

	public InvalidDateRangeException(String message) {
		super();
		this.message = message;
	}

	public InvalidDateRangeException() {
		super();
	}

	@Override
	public String toString() {
		return "InvalidDateRangeException Error :" + message;
	}
}
