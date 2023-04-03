package com.wipro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException  extends Exception{

	String msg;
	public AccountNotFoundException(String msg) {
		super(msg);
	}
	
}
