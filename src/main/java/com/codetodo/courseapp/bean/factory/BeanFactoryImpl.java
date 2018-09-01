package com.codetodo.courseapp.bean.factory;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.controller.command.course.AddCourseCommand;
import com.codetodo.courseapp.controller.command.course.CreateCourseCommand;
import com.codetodo.courseapp.controller.command.course.ListCoursesCommand;
import com.codetodo.courseapp.dao.course.impl.JDBCCourseDAO;
import com.codetodo.courseapp.dao.professor.impl.JDBCProfessorDAO;
import com.codetodo.courseapp.service.course.CourseService;
import com.codetodo.courseapp.service.course.impl.CourseServiceImpl;
import com.codetodo.courseapp.service.professor.impl.ProfessorServiceImpl;

public class BeanFactoryImpl implements BeanFactory {

	private static BeanFactory instance = null;

	private BeanFactoryImpl() {
	}

	@Override
	public Command getBean(String name) {
		if ("listCoursesCommand".equals(name)) {
			return new ListCoursesCommand(new CourseServiceImpl(new JDBCCourseDAO()));
		} else if ("addCourseCommand".equals(name)) {
			return new AddCourseCommand(new ProfessorServiceImpl(new JDBCProfessorDAO()));
		} else if ("createCourseCommand".equals(name)) {
			CourseService courseService = new CourseServiceImpl(new JDBCCourseDAO());

			return new CreateCourseCommand(courseService, new ListCoursesCommand(courseService));
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
