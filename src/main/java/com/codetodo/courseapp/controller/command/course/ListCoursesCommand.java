package com.codetodo.courseapp.controller.command.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.Validate;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.service.course.CourseService;

public class ListCoursesCommand implements Command {
	
	public static final String NAME = "listCoursesCommand";

	public static final String VIEW = "index.jsp";
	
	public static final String COURSE_ATTR_NAME = "courses";
	
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
		Validate.notNull(courseService, "'courseService' is required");
		
		List<Course> courses = courseService.findAll();

		request.setAttribute(COURSE_ATTR_NAME, courses);
		
		return VIEW;
	}

}
