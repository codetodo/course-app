package com.codetodo.courseapp.dao;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection getConnection();
}
