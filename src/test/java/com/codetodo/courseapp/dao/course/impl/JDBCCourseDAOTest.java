package com.codetodo.courseapp.dao.course.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codetodo.courseapp.dao.ConnectionFactory;
import com.codetodo.courseapp.dao.course.CourseDAO;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.model.Course.CourseLevel;
import com.codetodo.courseapp.model.Professor;

public class JDBCCourseDAOTest {

	private CourseDAO courseDAO;

	@Before
	public void setUp() throws Exception {
		courseDAO = new JDBCCourseDAO();

	}

	@Test
	public void shouldFindAllCourses() throws SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection conn = mock(Connection.class);
		PreparedStatement stmt = mock(PreparedStatement.class);
		ResultSet rs = mock(ResultSet.class);

		when(connectionFactory.getConnection()).thenReturn(conn);
		when(conn.prepareStatement(any())).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

		((JDBCCourseDAO) courseDAO).setConnectionFactory(connectionFactory);

		List<Course> courses = courseDAO.findAll();

		verify(connectionFactory).getConnection();
		verify(conn).close();
		verify(stmt).close();
		verify(rs).close();

		assertNotNull(courses);
		assertEquals(3, courses.size());
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowsControledExceptionWhenInvokeFindAllAndSQLExceptionIsThrownOut() throws SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection conn = mock(Connection.class);

		when(connectionFactory.getConnection()).thenReturn(conn);

		doThrow(new SQLException()).when(conn).prepareStatement(any());

		((JDBCCourseDAO) courseDAO).setConnectionFactory(connectionFactory);

		courseDAO.findAll();
	}

	@Test
	public void shouldCreateCourse() throws SQLException {
		Course course = new Course.Builder().setActive(true).setHours(50).setLevel(CourseLevel.ADVANCED).setTitle("Test Course")
				.setProfessor(new Professor.Builder().setId(2L).build()).build();
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection conn = mock(Connection.class);
		PreparedStatement stmt = mock(PreparedStatement.class);

		when(connectionFactory.getConnection()).thenReturn(conn);
		when(conn.prepareStatement(any())).thenReturn(stmt);

		((JDBCCourseDAO) courseDAO).setConnectionFactory(connectionFactory);

		courseDAO.create(course);

		verify(connectionFactory).getConnection();
		verify(conn).close();
		verify(stmt).close();
		verify(stmt).executeUpdate();
	}

}
