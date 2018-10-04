package com.codetodo.courseapp.service.course.impl;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.codetodo.courseapp.dao.course.CourseDAO;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.service.course.CourseService;

public class CourseServiceImpl implements CourseService {

	private CourseDAO courseDAO;

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	@Override
	public List<Course> findAll() {
		return courseDAO.findAll();
	}

	@Override
	public void create(Course course) {
		Validate.notNull(course);
		Validate.notNull(course.getProfessor());
		
		courseDAO.create(course);
	}

}
