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
	
	@ExceptionHandler(value= {CategoryNotFoundException.class})
	public ResponseEntity<Object> getCategoryNotFoundException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
	}
	
	@ExceptionHandler(value= {InvalidAuthTokenException.class})
	public ResponseEntity<Object> getInvalidAuthTokenException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
	
	@ExceptionHandler(value= {InvalidDateRangeException.class})
	public ResponseEntity<Object> getInvalidDateRangeException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
	
	@ExceptionHandler(value= {InvalidOnDateException.class})
	public ResponseEntity<Object> getInvalidOnDateException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
	
	@ExceptionHandler(value= {InvalidSortByException.class})
	public ResponseEntity<Object> getInvalidSortByException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
	
	@ExceptionHandler(value= {StatusNotFoundException.class})
	public ResponseEntity<Object> getStatusNotFoundException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
	
	@ExceptionHandler(value= {InvalidAdvertiseIdException.class})
	public ResponseEntity<Object> getInvalidAdvertiseIdException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
	
	@ExceptionHandler(value= {AdvertiseNotFoundException.class})
	public ResponseEntity<Object> getAdvertiseNotFoundException(RuntimeException exeption, WebRequest webRequest){
		return handleExceptionInternal(exeption, "{\"error\": \"" + exeption.toString() +"\"}", 
				new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}

}
