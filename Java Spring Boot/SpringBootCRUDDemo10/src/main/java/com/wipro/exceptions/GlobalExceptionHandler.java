package com.wipro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	//userdefined
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> handleNoDataException(NoDataFoundException e){
	
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	//predefined
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e){
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}
}
