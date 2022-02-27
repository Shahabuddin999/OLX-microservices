package com.olx.exception;
 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSortByException extends RuntimeException{
	private String message;

	public InvalidSortByException(String message) {
		super();
		this.message = message;
	}

	public InvalidSortByException() {
		super();
	}

	@Override
	public String toString() {
		return "InvalidSortByException Error :" + message;
	}
}
