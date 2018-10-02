package com.codetodo.courseapp.controller.command.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.model.Course;
import com.codetodo.courseapp.model.Course.CourseLevel;
import com.codetodo.courseapp.model.Professor;
import com.codetodo.courseapp.service.course.CourseService;

public class CreateCourseCommand implements Command {

	public final static String VIEW = "index.jsp";

	public final static String ACTIVE_PARAMETER_NAME = "active";
	public final static String PROFESSOR_PARAMETER_NAME = "professor";
	public final static String TITLE_PARAMETER_NAME = "title";
	public final static String HOURS_PARAMETER_NAME = "active";
	public final static String LEVEL_PARAMETER_NAME = "active";

	public final static String ACTIVE_TRUE_VALUE = "ON";

	private CourseService courseService;

	private Command listCoursesCommand;

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public void setListCoursesCommand(Command listCoursesCommand) {
		this.listCoursesCommand = listCoursesCommand;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String active = request.getParameter(ACTIVE_PARAMETER_NAME);
		String professorId = request.getParameter(PROFESSOR_PARAMETER_NAME);
		String title = request.getParameter(TITLE_PARAMETER_NAME);
		String hours = request.getParameter(HOURS_PARAMETER_NAME);
		String levelId = request.getParameter(LEVEL_PARAMETER_NAME);

		Course course = new Course.Builder().setActive(ACTIVE_TRUE_VALUE.equals(active))
				.setProfessor(new Professor.Builder().setId(Long.valueOf(professorId)).build()).setTitle(title)
				.setHours(Integer.valueOf(hours)).setLevel(CourseLevel.fromId(levelId)).build();

		courseService.create(course);

		request.setAttribute("createdCourse", course);

		return listCoursesCommand.execute(request, response);
	}

}