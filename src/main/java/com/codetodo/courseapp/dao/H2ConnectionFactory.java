package com.codetodo.courseapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionFactory implements ConnectionFactory {

	private String url = "jdbc:h2:~/test";
	private String user = "sa";
	private String pass = "";

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Connection getConnection() {
		try {
			org.h2.Driver.load();
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException ex) {
			throw new DataAccessException("An error ocurred ");
		}
	}
}
