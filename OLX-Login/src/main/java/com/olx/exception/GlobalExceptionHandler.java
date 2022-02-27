package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value= {InvalidUserNameOrPasswordException.class})
	public ResponseEntity<Object> getInvalidUserNameOrPasswordException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
	
	@ExceptionHandler(value= {InvalidAuthTokenException.class})
	public ResponseEntity<Object> getInvalidAuthTokenException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
	
	@ExceptionHandler(value= {UserNameAlreadyExistsException.class})
	public ResponseEntity<Object> getUserNameAlreadyExistsException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
	}

}
