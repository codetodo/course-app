package com.codetodo.courseapp.controller.command.factory;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.codetodo.courseapp.bean.factory.BeanFactory;
import com.codetodo.courseapp.bean.factory.BeanFactoryImpl;
import com.codetodo.courseapp.controller.command.Command;

public class CommandFactoryImpl implements CommandFactory {

	private final static Map<String, String> commandNames = new HashMap<>();

	static {
		commandNames.put("GET", "listCoursesCommand");
		commandNames.put("GET|courses", "listCoursesCommand");
		commandNames.put("GET|courses/add", "addCourseCommand");		
		commandNames.put("POST|courses", "createCourseCommand");
	}

	private static CommandFactory instance;

	private final BeanFactory beanFactory;

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

		return (Command)beanFactory.getBean(name);
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