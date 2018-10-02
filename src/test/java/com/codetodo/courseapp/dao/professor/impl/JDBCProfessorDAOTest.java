package com.codetodo.courseapp.dao.professor.impl;

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
import com.codetodo.courseapp.dao.professor.ProfessorDAO;
import com.codetodo.courseapp.model.Professor;

public class JDBCProfessorDAOTest {

	private ProfessorDAO professorDAO;

	@Before
	public void setUp() {
		professorDAO = new JDBCProfessorDAO();
	}

	@Test
	public void shouldFindAllProfessors() throws SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection conn = mock(Connection.class);
		PreparedStatement stmt = mock(PreparedStatement.class);
		ResultSet rs = mock(ResultSet.class);

		when(connectionFactory.getConnection()).thenReturn(conn);
		when(conn.prepareStatement(any())).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

		((JDBCProfessorDAO) professorDAO).setConnectionFactory(connectionFactory);

		List<Professor> professors = professorDAO.findAll();

		verify(connectionFactory).getConnection();
		verify(conn).close();
		verify(stmt).close();
		verify(rs).close();

		assertNotNull(professors);
		assertEquals(3, professors.size());
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowsControledExceptionWhenInvokeFindAllAndSQLExceptionIsThrownOut() throws SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection conn = mock(Connection.class);

		when(connectionFactory.getConnection()).thenReturn(conn);

		doThrow(new SQLException()).when(conn).prepareStatement(any());

		((JDBCProfessorDAO) professorDAO).setConnectionFactory(connectionFactory);

		professorDAO.findAll();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowUnsupportedOperationExceptionWhenInvokeFindById() throws SQLException {
		Long id = 2l;
		professorDAO.findById(id);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowUnsupportedOperationExceptionWhenInvokeDelete() throws SQLException {
		Long id = 2l;
		professorDAO.delete(id);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowUnsupportedOperationExceptionWhenInvoke() throws SQLException {
		Professor professor = null;
		professorDAO.update(professor);
	}

}
