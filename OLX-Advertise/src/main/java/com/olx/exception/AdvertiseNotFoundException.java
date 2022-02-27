package com.olx.exception;
 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AdvertiseNotFoundException extends RuntimeException{
	private String message;

	public AdvertiseNotFoundException(String message) {
		super();
		this.message = message;
	}

	public AdvertiseNotFoundException() {
		super();
	}

	@Override
	public String toString() {
		return "AdvertiseNotFoundException Error :" + message;
	}
}
