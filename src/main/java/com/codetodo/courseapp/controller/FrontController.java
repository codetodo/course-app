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

	public FrontController() {
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		String view = null;

		try {
			CommandFactory commandFactory = CommandFactoryImpl.getInstance();
			Command command = commandFactory.create("GET|courses");

			view = command.execute(request, response);

		} catch (Exception e) {
			view = "error.jsp";
		}

		dispatch(request, response, view);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String view)
			throws javax.servlet.ServletException, java.io.IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + view);
		dispatcher.forward(request, response);
	}

}
