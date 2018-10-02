package com.codetodo.courseapp.controller.command.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.service.course.CourseService;

public class ListCoursesCommand implements Command {

	public final static String VIEW = "index.jsp";
	public final static String COURSE_ATTR_NAME = "courses";
	
	private CourseService courseService;

	public ListCoursesCommand() {
	}

	public ListCoursesCommand(CourseService courseService) {
		this.courseService = courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Course> courses = courseService.findAll();

		request.setAttribute(COURSE_ATTR_NAME, courses);
		
		return VIEW;
	}

}
