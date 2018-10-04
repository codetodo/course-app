package com.codetodo.courseapp.bean.factory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

public class BeanCreationExceptionTest {

	@Test
	public void testInitWithDefaultConstructor() {
		BeanCreationException exception = new BeanCreationException();

		assertThat(exception.getMessage(), is(nullValue()));
	}

	@Test
	public void testInitWithMessage() {
		String message = "an error ocurred";

		BeanCreationException exception = new BeanCreationException(message);

		assertThat(exception.getMessage(), is(notNullValue()));
	}

	@Test
	public void testInitWithMessageAndException() {
		String message = "error message";
		Exception ex = new RuntimeException();

		BeanCreationException exception = new BeanCreationException(message, ex);

		assertThat(exception.getMessage(), is(notNullValue()));
		assertThat(exception, is(notNullValue()));
	}

}
