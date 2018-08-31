package com.codetodo.courseapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	static final String DB_URL = "jdbc:h2:~/test";
	static final String USER = "sa";
	static final String PASS = "";

	public static Connection getConnection() {
		try {
			org.h2.Driver.load();
			return DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException ex) {
			throw new RuntimeException("An error ocurred ");
		}
	}

}
