package com.codetodo.courseapp.service.course;

import java.util.List;

import com.codetodo.courseapp.model.Course;

public interface CourseService {
	List<Course> findAll();
	void create(Course course);
}
