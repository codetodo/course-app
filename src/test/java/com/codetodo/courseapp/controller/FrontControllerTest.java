package com.codetodo.courseapp.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.controller.command.factory.CommandFactory;

public class FrontControllerTest {

	private static final String CONTEXT_PATH = "/app";
	private HttpServlet httpServlet;

	private CommandFactory commandFactory;

	@Before
	public void setUp() throws Exception {
		commandFactory = mock(CommandFactory.class);

		httpServlet = spy(new FrontController(commandFactory));
	}

	@Test
	public void shouldProcessGetRequest() throws ServletException, IOException {
		String view = "home";

		ServletRequest req = mock(HttpServletRequest.class);
		ServletResponse res = mock(HttpServletResponse.class);
		ServletContext servletContext = mock(ServletContext.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		Command command = mock(Command.class);

		when(((HttpServletRequest) req).getMethod()).thenReturn("GET");
		when(((HttpServletRequest) req).getRequestURI()).thenReturn(CONTEXT_PATH);
		when(((HttpServletRequest) req).getContextPath()).thenReturn(CONTEXT_PATH);

		when(commandFactory.create((HttpServletRequest) req)).thenReturn(command);
		when(command.execute((HttpServletRequest) req, (HttpServletResponse) res)).thenReturn(view);

		doReturn(servletContext).when(httpServlet).getServletContext();

		when(servletContext.getRequestDispatcher(any())).thenReturn(dispatcher);

		httpServlet.service(req, (ServletResponse) res);

		verify(dispatcher, times(1)).forward(req, res);
	}

	@Test
	public void shouldProcessPostRequest() throws ServletException, IOException {
		String view = "index";

		ServletRequest req = mock(HttpServletRequest.class);
		ServletResponse res = mock(HttpServletResponse.class);
		ServletContext servletContext = mock(ServletContext.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		Command command = mock(Command.class);

		when(((HttpServletRequest) req).getMethod()).thenReturn("POST");
		when(((HttpServletRequest) req).getRequestURI()).thenReturn(CONTEXT_PATH);
		when(((HttpServletRequest) req).getContextPath()).thenReturn(CONTEXT_PATH);

		when(commandFactory.create((HttpServletRequest) req)).thenReturn(command);
		when(command.execute((HttpServletRequest) req, (HttpServletResponse) res)).thenReturn(view);

		doReturn(servletContext).when(httpServlet).getServletContext();

		when(servletContext.getRequestDispatcher(any())).thenReturn(dispatcher);

		httpServlet.service(req, (ServletResponse) res);

		verify(dispatcher, times(1)).forward(req, res);
	}

	@Test
	public void shouldDispatchToErrorPageWhenAnExceptionOcurred() throws ServletException, IOException {
		ServletRequest req = mock(HttpServletRequest.class);
		ServletResponse res = mock(HttpServletResponse.class);
		ServletContext servletContext = mock(ServletContext.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		Command command = mock(Command.class);

		when(((HttpServletRequest) req).getMethod()).thenReturn("POST");
		when(((HttpServletRequest) req).getRequestURI()).thenReturn(CONTEXT_PATH);
		when(((HttpServletRequest) req).getContextPath()).thenReturn(CONTEXT_PATH);

		when(commandFactory.create((HttpServletRequest) req)).thenReturn(command);
		doThrow(RuntimeException.class).when(command).execute((HttpServletRequest) req, (HttpServletResponse) res);

		doReturn(servletContext).when(httpServlet).getServletContext();

		when(servletContext.getRequestDispatcher(any())).thenReturn(dispatcher);

		httpServlet.service(req, (ServletResponse) res);

		verify(dispatcher, times(1)).forward(req, res);
	}

}
