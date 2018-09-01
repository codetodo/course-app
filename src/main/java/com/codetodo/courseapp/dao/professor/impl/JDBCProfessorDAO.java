package com.codetodo.courseapp.dao.professor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codetodo.courseapp.dao.ConnectionFactory;
import com.codetodo.courseapp.dao.professor.ProfessorDAO;
import com.codetodo.courseapp.model.Professor;

public class JDBCProfessorDAO implements ProfessorDAO {

	@Override
	public List<Professor> findAll() {
		List<Professor> result = new ArrayList<>();

		String sql = "SELECT p.id, p.name, p.email FROM professors p ORDER BY p.name";

		try (Connection c = ConnectionFactory.getConnection();
				PreparedStatement stmt = c.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				result.add(new Professor.Builder().setId(rs.getLong("id")).setName(rs.getString("name"))
						.setEmail(rs.getString("email")).build());
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
	}

	@Override
	public Professor findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Professor entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Professor entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Long id) {
		throw new UnsupportedOperationException();
	}

}
