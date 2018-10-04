package com.codetodo.courseapp.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class DataAccessExceptionTest {

	@Test
	public void testInitWithDefaultConstructor() {
		DataAccessException exception = new DataAccessException();

		assertThat(exception.getMessage(), is(nullValue()));
	}

	@Test
	public void testInitWithMessage() {
		String message = "Failed accessing data";

		DataAccessException exception = new DataAccessException(message);

		assertThat(exception.getMessage(), is(notNullValue()));
	}

}
