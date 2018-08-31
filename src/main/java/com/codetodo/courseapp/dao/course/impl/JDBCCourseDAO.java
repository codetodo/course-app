package com.codetodo.courseapp.dao.course.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codetodo.courseapp.dao.ConnectionFactory;
import com.codetodo.courseapp.dao.course.CourseDAO;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.model.Course.CourseLevel;

public class JDBCCourseDAO implements CourseDAO {

	@Override
	public List<Course> findAll() {
		List<Course> result = new ArrayList<>();

		String sql = "SELECT c.id, c.title, c.level, c.hours FROM courses c ORDER BY c.title";

		try (Connection c = ConnectionFactory.getConnection();
				PreparedStatement stmt = c.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				result.add(new Course.Builder().setId(rs.getLong("id")).setTitle(rs.getString("title"))
						.setHours(rs.getInt("hours")).setLevel(CourseLevel.fromString(rs.getString("level"))).build());
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
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
