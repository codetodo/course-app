package com.codetodo.courseapp.controller.command.factory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import com.codetodo.courseapp.bean.factory.BeanFactory;
import com.codetodo.courseapp.bean.factory.BeanFactoryImpl;
import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.controller.command.course.ListCoursesCommand;

public class CommandFactoryImplTest {

	public static final String CONTEXT_PATH = "/course-app";
	public static final String COURSES_RESOURCE = "courses";

	@Before
	public void setUp()
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field instance = CommandFactoryImpl.class.getDeclaredField("instance");
		instance.setAccessible(true);
		instance.set(null, null);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowExceptionWhenInvokeCreateAndThereIsNoCommandRegisteredForGivenOperation() {
		String httpMethod = "GET";

		BeanFactory factory = mock(BeanFactory.class);

		CommandFactory commandFactory = CommandFactoryImpl.getInstance(factory);

		HttpServletRequest request = mock(HttpServletRequest.class);

		when(request.getMethod()).thenReturn(httpMethod);
		when(request.getRequestURI()).thenReturn(CONTEXT_PATH + "/professors");
		when(request.getContextPath()).thenReturn(CONTEXT_PATH);

		commandFactory.create(request);
	}

	@Test
	public void shouldReturnListCourseCommandWhenInvokeCreateWithGetHttpMethodAndPathIsCourses() {
		String httpMethod = "GET";

		BeanFactory factory = mock(BeanFactory.class);

		CommandFactory commandFactory = CommandFactoryImpl.getInstance(factory);

		HttpServletRequest request = mock(HttpServletRequest.class);

		when(request.getMethod()).thenReturn(httpMethod);
		when(request.getRequestURI()).thenReturn(CONTEXT_PATH + "/" + COURSES_RESOURCE);
		when(request.getContextPath()).thenReturn(CONTEXT_PATH);

		when(factory.getBean(ListCoursesCommand.NAME)).thenReturn(new ListCoursesCommand());

		Command command = commandFactory.create(request);

		verify(factory).getBean(ListCoursesCommand.NAME);

		assertNotNull(command);
		assertTrue(command instanceof ListCoursesCommand);
	}

	@Test
	public void shouldReturnListCourseCommandWhenInvokeCreateWithGetHttpMethod() {
		String httpMethod = "GET";

		BeanFactory factory = mock(BeanFactory.class);

		CommandFactory commandFactory = CommandFactoryImpl.getInstance(factory);

		HttpServletRequest request = mock(HttpServletRequest.class);

		when(request.getMethod()).thenReturn(httpMethod);
		when(request.getRequestURI()).thenReturn(CONTEXT_PATH);
		when(request.getContextPath()).thenReturn(CONTEXT_PATH);

		when(factory.getBean(ListCoursesCommand.NAME)).thenReturn(new ListCoursesCommand());

		Command command = commandFactory.create(request);

		verify(factory).getBean(ListCoursesCommand.NAME);

		assertNotNull(command);
		assertTrue(command instanceof ListCoursesCommand);
	}

	@Test
	public void shouldCreateInstanceWithDefaultBeanFactory() {
		CommandFactory commandFactory = CommandFactoryImpl.getInstance(null);
		assertTrue(((CommandFactoryImpl)commandFactory).getBeanFactory() instanceof BeanFactoryImpl);

	}

}
