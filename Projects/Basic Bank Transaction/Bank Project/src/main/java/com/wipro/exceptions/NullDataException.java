package com.wipro.exceptions;

public class NullDataException extends Exception {

	String msg;

	public NullDataException(String msg) {
		super(msg);
	}
}
