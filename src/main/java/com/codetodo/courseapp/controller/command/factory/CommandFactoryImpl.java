package com.codetodo.courseapp.controller.command.factory;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.codetodo.courseapp.bean.factory.BeanFactory;
import com.codetodo.courseapp.bean.factory.BeanFactoryImpl;
import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.controller.command.course.AddCourseCommand;
import com.codetodo.courseapp.controller.command.course.CreateCourseCommand;
import com.codetodo.courseapp.controller.command.course.ListCoursesCommand;

public class CommandFactoryImpl implements CommandFactory {

	private static final Map<String, String> commandNames = new HashMap<>();

	static {
		commandNames.put("GET", ListCoursesCommand.NAME);
		commandNames.put("GET|courses", ListCoursesCommand.NAME);
		commandNames.put("GET|courses/add", AddCourseCommand.NAME);
		commandNames.put("POST|courses", CreateCourseCommand.NAME);
	}

	private static CommandFactory instance;

	private final BeanFactory beanFactory;

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	private CommandFactoryImpl(BeanFactory beanFactory) {
		this.beanFactory = beanFactory == null ? getDefaultBeanFactory() : beanFactory;
	}

	@Override
	public Command create(HttpServletRequest request) {
		String op = getOperationFrom(request);

		String name = commandNames.get(op);
		if (name == null) {
			throw new IllegalStateException("There is no registered command for operation " + op);
		}

		return (Command) beanFactory.getBean(name);
	}

	public static CommandFactory getInstance() {
		return getInstance(null);
	}

	public static CommandFactory getInstance(BeanFactory factory) {
		if (instance == null) {
			instance = new CommandFactoryImpl(factory);
		}
		return instance;
	}

	private String getOperationFrom(HttpServletRequest request) {
		String httpMethod = request.getMethod();
		String resource = request.getRequestURI().substring(request.getContextPath().length());

		if (resource.startsWith("/")) {
			resource = resource.substring(1);
		}

		String separator = resource.isEmpty() ? "" : "|";

		return httpMethod + separator + resource;
	}

	private BeanFactory getDefaultBeanFactory() {
		return BeanFactoryImpl.getInstance();
	}

}
