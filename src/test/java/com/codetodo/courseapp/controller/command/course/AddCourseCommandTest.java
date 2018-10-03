package com.codetodo.courseapp.controller.command.course;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.codetodo.courseapp.model.Professor;
import com.codetodo.courseapp.service.professor.ProfessorService;

public class AddCourseCommandTest {

	private AddCourseCommand addCourseCommand;

	@Before
	public void setUp() throws Exception {
		addCourseCommand = new AddCourseCommand();
	}

	@Test
	public void shouldReturnAddViewWhenInvokeExecute() {
		List<Professor> professors = getProffesors();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		ProfessorService professorService = mock(ProfessorService.class);
		when(professorService.findAll()).thenReturn(professors);
		addCourseCommand.setProfessorService(professorService);
		
		String resultView = addCourseCommand.execute(request, response);

		verify(request, times(1)).setAttribute(AddCourseCommand.PROFESSORS_ATTR_NAME, professors);
		assertEquals(AddCourseCommand.VIEW, resultView);
	}

	@Test(expected = Exception.class)
	public void shouldFailWhenInvokeExecuteAndProfessorServiceIsNotSet() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		ProfessorService professorService = null;
		addCourseCommand = new AddCourseCommand(professorService);

		addCourseCommand.execute(request, response);
	}

	private List<Professor> getProffesors() {
		return Arrays.asList(new Professor.Builder().setName("Juan").build());
	}

}
