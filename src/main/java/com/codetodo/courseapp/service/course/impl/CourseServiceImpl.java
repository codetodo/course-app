package com.codetodo.courseapp.service.course.impl;

import java.util.List;

import com.codetodo.courseapp.dao.course.CourseDAO;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.service.course.CourseService;

public class CourseServiceImpl implements CourseService {

	private CourseDAO courseDAO;

	public CourseServiceImpl() {

	}

	public CourseServiceImpl(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	@Override
	public List<Course> findAll() {
		return courseDAO.findAll();
	}

	@Override
	public void create(Course course) {
		courseDAO.create(course);
	}

}
