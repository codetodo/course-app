package com.codetodo.courseapp.controller.command.course;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.codetodo.courseapp.model.Course.CourseLevel;
import com.codetodo.courseapp.service.course.CourseService;

public class CreateCourseCommandTest {

	private CreateCourseCommand createCourseCommand;

	@Before
	public void setUp() throws Exception {
		createCourseCommand = new CreateCourseCommand();
	}

	@Test
	public void shouldReturnCreateCourseViewWhenInvokeExecute() {
		String courseTitle = "Java 8 Introduction";
		String hours = "240";
		String professorId = "01";
		String level = CourseLevel.ADVANCED.getId();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		CourseService courseService = mock(CourseService.class);
		ListCoursesCommand listCoursesCommand = mock(ListCoursesCommand.class);

		createCourseCommand.setCourseService(courseService);
		createCourseCommand.setListCoursesCommand(listCoursesCommand);

		when(request.getParameter(CreateCourseCommand.ACTIVE_PARAMETER_NAME))
				.thenReturn(CreateCourseCommand.ACTIVE_TRUE_VALUE);
		when(request.getParameter(CreateCourseCommand.PROFESSOR_PARAMETER_NAME)).thenReturn(professorId);
		when(request.getParameter(CreateCourseCommand.TITLE_PARAMETER_NAME)).thenReturn(courseTitle);
		when(request.getParameter(CreateCourseCommand.HOURS_PARAMETER_NAME)).thenReturn(hours);
		when(request.getParameter(CreateCourseCommand.LEVEL_PARAMETER_NAME)).thenReturn(level);

		when(listCoursesCommand.execute(request, response)).thenReturn(ListCoursesCommand.VIEW);

		String resultView = createCourseCommand.execute(request, response);

		verify(courseService, times(1)).create(any());
		verify(listCoursesCommand, times(1)).execute(request, response);

		assertEquals(CreateCourseCommand.VIEW, resultView);
	}

}
