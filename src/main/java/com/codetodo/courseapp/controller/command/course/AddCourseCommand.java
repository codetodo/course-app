package com.codetodo.courseapp.controller.command.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codetodo.courseapp.controller.command.Command;

public class AddCourseCommand implements Command {
	
	public final static String VIEW = "views/course/add.jsp";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return VIEW;
	}

}
