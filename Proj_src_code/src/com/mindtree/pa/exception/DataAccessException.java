package com.mindtree.pa.exception;

public class DataAccessException extends Exception {

	public DataAccessException(String message, Exception exception) {
		super(message, exception);
	}

}
