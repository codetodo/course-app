package com.codetodo.courseapp.service.course.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codetodo.courseapp.dao.course.CourseDAO;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.service.course.CourseService;

public class CourseServiceImplTest {

	private CourseService courseService;
	
	@Before
	public void setUp() throws Exception {
		courseService = new CourseServiceImpl();
	}

	@Test
	public void test() {
		List<Course> courses = Arrays.asList(new Course.Builder().setTitle("Test course").build());		
		CourseDAO courseDAO = mock(CourseDAO.class);		
		
		when(courseDAO.findAll()).thenReturn(courses);
		
		((CourseServiceImpl)courseService).setCourseDAO(courseDAO );
		
		List<Course> result = courseService.findAll();
		
		verify(courseDAO, times(1)).findAll();
		
		assertNotNull(result);
	}

}
