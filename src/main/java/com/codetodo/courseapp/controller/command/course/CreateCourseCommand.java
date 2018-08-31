package com.codetodo.courseapp.controller.command.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codetodo.courseapp.controller.command.Command;

public class CreateCourseCommand implements Command {

	public final static String VIEW = "index.jsp";
	
	private Command listCourseCommand;
	
	public CreateCourseCommand(Command listCourseCommand) {
		this.listCourseCommand = listCourseCommand;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return listCourseCommand.execute(request, response);
	}

}
