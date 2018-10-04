package com.codetodo.courseapp.dao;

@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException {

	public DataAccessException() {

	}

	public DataAccessException(String errorMessage) {
		super(errorMessage);
	}

}
