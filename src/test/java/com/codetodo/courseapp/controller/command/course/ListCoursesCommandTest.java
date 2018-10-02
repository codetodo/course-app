package com.codetodo.courseapp.controller.command.course;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.service.course.CourseService;

public class ListCoursesCommandTest {
	
	private ListCoursesCommand listCoursesCommand;

	@Before
	public void setUp() throws Exception {
		listCoursesCommand = new ListCoursesCommand();
	}

	@Test
	public void shouldReturnListViewWhenInvokeExecute() {
		List<Course> courses = getCourses();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		CourseService courseService = mock(CourseService.class);
		
		listCoursesCommand.setCourseService(courseService);
		
		
		when(courseService.findAll()).thenReturn(courses);
		
		String resultView = listCoursesCommand.execute(request, response);
		
		verify(courseService, times(1)).findAll();
		verify(request, times(1)).setAttribute(ListCoursesCommand.COURSE_ATTR_NAME, courses);
		
		assertEquals(ListCoursesCommand.VIEW, resultView);
	}

	private List<Course> getCourses() {
		return Arrays.asList(new Course.Builder().setTitle("Java").build());
	}

}
