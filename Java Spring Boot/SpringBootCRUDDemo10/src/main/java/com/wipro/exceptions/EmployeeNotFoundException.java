package com.wipro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException  extends Exception{

	String msg;
	public EmployeeNotFoundException(String msg) {
		super(msg);
	}
	
}
