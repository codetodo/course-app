package com.codetodo.courseapp.dao.course.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codetodo.courseapp.dao.ConnectionFactory;
import com.codetodo.courseapp.dao.DataAccessException;
import com.codetodo.courseapp.dao.course.CourseDAO;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.model.Course.CourseLevel;
import com.codetodo.courseapp.model.Professor;

public class JDBCCourseDAO implements CourseDAO {

	private ConnectionFactory connectionFactory;

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public List<Course> findAll() {
		List<Course> result = new ArrayList<>();

		String sql = "SELECT c.id, c.title, c.level, c.hours, p.name FROM COURSES c INNER JOIN PROFESSORS p on p.id = c.professor_id WHERE c.active = true ORDER BY c.title";

		try (Connection c = connectionFactory.getConnection();
				PreparedStatement stmt = c.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				result.add(new Course.Builder().setId(rs.getLong("id")).setTitle(rs.getString("title"))
						.setHours(rs.getInt("hours")).setLevel(CourseLevel.fromName(rs.getString("level")))
						.setProfessor(new Professor.Builder().setName(rs.getString("name")).build()).build());
			}

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		}

		return result;
	}

	@Override
	public Course findById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void create(Course course) {
		String sql = "INSERT INTO COURSES(TITLE,LEVEL,HOURS,ACTIVE,PROFESSOR_ID) VALUES (?,?,?,?,?)";

		try (Connection c = connectionFactory.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {

			stmt.setString(1, course.getTitle());
			stmt.setString(2, course.getLevel().toString());
			stmt.setInt(3, course.getHours());
			stmt.setBoolean(4, course.isActive());
			stmt.setLong(5, course.getProfessor().getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		}
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
