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

	private CourseService courseService;

	private Command listCourseCommand;

	public CreateCourseCommand(CourseService courseService, Command listCourseCommand) {
		this.courseService = courseService;
		this.listCourseCommand = listCourseCommand;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String active = request.getParameter("active");
		String professorId = request.getParameter("professor");
		String title = request.getParameter("title");
		String hours = request.getParameter("hours");
		String levelId = request.getParameter("level");

		Course course = new Course.Builder().setActive("on".equals(active))
				.setProfessor(new Professor.Builder().setId(Long.valueOf(professorId)).build()).setTitle(title)
				.setHours(Integer.valueOf(hours)).setLevel(CourseLevel.fromId(levelId)).build();

		courseService.create(course);
		
		request.setAttribute("createdCourse", course);

		return listCourseCommand.execute(request, response);
	}

}
