package com.olx.exception;
 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotFoundException extends RuntimeException{
	private String message;

	public CategoryNotFoundException(String message) {
		super();
		this.message = message;
	}

	public CategoryNotFoundException() {
		super();
	}

	@Override
	public String toString() {
		return "CategoryNotFoundException Error :" + message;
	}
	
}
