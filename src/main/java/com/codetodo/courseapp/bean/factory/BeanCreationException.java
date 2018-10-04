package com.codetodo.courseapp.bean.factory;

@SuppressWarnings("serial")
public class BeanCreationException extends RuntimeException {

	public BeanCreationException() {

	}

	public BeanCreationException(String errorMessage) {
		this(errorMessage, null);
	}

	public BeanCreationException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

}
