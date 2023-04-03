package com.wipro.exceptions;

//@ResponseStatus(HttpStatus.NOT_FOUND) // other way to handle 
public class NoDataFoundException  extends Exception{

	String message;
	
	public NoDataFoundException(String message) {
		super(message);
	}
}
