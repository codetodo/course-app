package com.codetodo.courseapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.controller.command.factory.CommandFactory;
import com.codetodo.courseapp.controller.command.factory.CommandFactoryImpl;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String ERROR_VIEW = "views/errors/general-error.jsp";

	private CommandFactory commandFactory;

	public FrontController() {
	}

	public FrontController(CommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		String view = null;

		try {
			Command command = getCommandFactory().create(request);

			view = command.execute(request, response);

		} catch (Exception e) {
			view = ERROR_VIEW;
		}

		dispatch(request, response, view);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String view)
			throws javax.servlet.ServletException, java.io.IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + view);
		dispatcher.forward(request, response);
	}

	private CommandFactory getCommandFactory() {
		if (commandFactory == null) {
			commandFactory = CommandFactoryImpl.getInstance();
		}
		return commandFactory;
	}

}
