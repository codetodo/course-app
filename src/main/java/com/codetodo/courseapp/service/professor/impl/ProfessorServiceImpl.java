package com.codetodo.courseapp.service.professor.impl;

import java.util.List;

import com.codetodo.courseapp.dao.professor.ProfessorDAO;
import com.codetodo.courseapp.model.Professor;
import com.codetodo.courseapp.service.professor.ProfessorService;

public class ProfessorServiceImpl implements ProfessorService {

	private ProfessorDAO professorDAO;

	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}

	@Override
	public List<Professor> findAll() {
		return professorDAO.findAll();
	}

}
