package com.codetodo.courseapp.dao.course.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.codetodo.courseapp.dao.course.CourseDAO;
import com.codetodo.courseapp.model.Course;

public class JDBCCourseDAO implements CourseDAO {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:~/test";

	// Database credentials
	static final String USER = "sa";
	static final String PASS = "";

	@Override
	public List<Course> findAll() {
//		Connection conn = null;
//		Statement stmt = null;
//		try {
//			// STEP 1: Register JDBC driver
//			Class.forName(JDBC_DRIVER);
//
//			// STEP 2: Open a connection
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//			// STEP 3: Execute a query
//			System.out.println("Creating table in given database...");
//			stmt = conn.createStatement();
//			String sql = "CREATE TABLE   COURSES " + "(id INTEGER PRIMARY KEY, " + " title VARCHAR(255) NOT NULL, "
//					+ " hours INTEGER NOT NULL, " + " level VARCHAR(40))";
//			stmt.executeUpdate(sql);
//			System.out.println("Created table in given database...");
//
//			// STEP 4: Clean-up environment
//			stmt.close();
//			conn.close();
//		} catch (SQLException se) {
//			// Handle errors for JDBC
//			se.printStackTrace();
//		} catch (Exception e) {
//			// Handle errors for Class.forName
//			e.printStackTrace();
//		} finally {
//			// finally block used to close resources
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException se2) {
//			} // nothing we can do
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			} // end finally try
//		} // end try
//		System.out.println("Goodbye!");

		List<Course> courses = new ArrayList<>();

		courses.add(new Course.Builder().setTitle("OOP with Java").build());
		courses.add(new Course.Builder().setTitle("Spring").build());
		courses.add(new Course.Builder().setTitle("Angular").build());
		courses.add(new Course.Builder().setTitle("Design Pattern").build());

		return courses;
	}

	@Override
	public Course findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Course entity) {
		// TODO
		return null;
	}

	@Override
	public void update(Course entity) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void delete(Long id) {
		throw new UnsupportedOperationException();
	}

}
