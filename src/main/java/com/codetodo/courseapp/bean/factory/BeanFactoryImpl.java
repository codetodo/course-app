package com.codetodo.courseapp.bean.factory;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.controller.command.course.AddCourseCommand;
import com.codetodo.courseapp.controller.command.course.CreateCourseCommand;
import com.codetodo.courseapp.controller.command.course.ListCoursesCommand;
import com.codetodo.courseapp.dao.course.impl.JDBCCourseDAO;
import com.codetodo.courseapp.service.course.impl.CourseServiceImpl;

public class BeanFactoryImpl implements BeanFactory {

	private static BeanFactory instance = null;
	
	private BeanFactoryImpl() {		
	}
	
	@Override
	public Command getBean(String name) {
		if ("listCoursesCommand".equals(name)) {
			return new ListCoursesCommand(new CourseServiceImpl(new JDBCCourseDAO()));
		} else if ("addCourseCommand".equals(name)) {
			return new AddCourseCommand();
		} else if ("createCourseCommand".equals(name)) {
			return new CreateCourseCommand(new ListCoursesCommand(new CourseServiceImpl(new JDBCCourseDAO())));
		}	
		return null;
	}

	public static BeanFactory getInstance() {
		if (instance == null) {
			instance = new BeanFactoryImpl();
		}
		return instance;
	}

}
