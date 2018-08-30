package com.codetodo.courseapp.dao.course.impl;

import java.util.ArrayList;
import java.util.List;

import com.codetodo.courseapp.dao.course.CourseDAO;
import com.codetodo.courseapp.model.Course;

public class JDBCCourseDAO implements CourseDAO {

	@Override
	public List<Course> findAll() {
		List<Course> courses = new ArrayList<>();
		
		courses.add(new Course.Builder().setTitle("OOP with Java").build());
		
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
