package com.codetodo.courseapp.controller.command.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.codetodo.courseapp.bean.factory.BeanFactory;
import com.codetodo.courseapp.bean.factory.BeanFactoryImpl;
import com.codetodo.courseapp.controller.command.Command;

public class CommandFactoryImpl implements CommandFactory {

	private final static Map<String, String> commandNames = new HashMap<>();

	static {
		commandNames.put("GET|courses", "listCoursesCommand");
	}

	private static CommandFactory instance;

	private final BeanFactory factory;

	private CommandFactoryImpl(BeanFactory factory) {
		this.factory = factory == null ? getDefaultBeanFactory() : factory;
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

	@Override
	public Command create(String operation) {
		Objects.requireNonNull(operation, "'operation' must not be null");

		String name = commandNames.get(operation);
		if (name == null) {
			throw new IllegalStateException("There is no registered command for operation " + operation);
		}

		return factory.getBean(name);
	}

	private BeanFactory getDefaultBeanFactory() {
		return BeanFactoryImpl.getInstance();
	}

}
