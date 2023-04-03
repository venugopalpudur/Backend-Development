package com.wipro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException  extends Exception{

	String msg;
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
	
}
