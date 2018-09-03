package com.codetodo.courseapp.service.professor.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codetodo.courseapp.dao.professor.ProfessorDAO;
import com.codetodo.courseapp.model.Professor;
import com.codetodo.courseapp.service.professor.ProfessorService;

public class ProfessorServiceImplTest {

    private ProfessorService professorService;
	
	@Before
	public void setUp() throws Exception {
		professorService = new ProfessorServiceImpl();
	}

	@Test
	public void shouldFindAll() {
		List<Professor> professors = Arrays.asList(new Professor.Builder().build());		
		ProfessorDAO professorDAO = mock(ProfessorDAO.class);		
		
		when(professorDAO.findAll()).thenReturn(professors);
		
		((ProfessorServiceImpl)professorService).setProfessorDAO(professorDAO );
		
		List<Professor> result = professorService.findAll();
		
		verify(professorDAO, times(1)).findAll();
		
		assertNotNull(result);
	}

}
