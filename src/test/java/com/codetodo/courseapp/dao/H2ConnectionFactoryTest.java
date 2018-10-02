package com.codetodo.courseapp.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class H2ConnectionFactoryTest {
	
	private ConnectionFactory connectionFactory = new H2ConnectionFactory();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldGetConnection() {
	    String url = "jdbc:h2:~/test";
	    String user = "sa";
	    String pass = "";
		
		((H2ConnectionFactory)connectionFactory).setPass(pass);
		((H2ConnectionFactory)connectionFactory).setUrl(url);
		
		((H2ConnectionFactory)connectionFactory).setUser(user);
		
		Connection conn = connectionFactory.getConnection();
		
		assertNotNull(conn);
	}

}
