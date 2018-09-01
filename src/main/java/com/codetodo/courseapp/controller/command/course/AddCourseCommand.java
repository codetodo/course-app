package com.codetodo.courseapp.controller.command.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.dto.course.LevelDTO;
import com.codetodo.courseapp.model.Professor;
import com.codetodo.courseapp.model.Course.CourseLevel;
import com.codetodo.courseapp.service.professor.ProfessorService;

public class AddCourseCommand implements Command {

	public final static String VIEW = "views/course/add.jsp";

	private ProfessorService profesorService;

	public AddCourseCommand() {
	}

	public AddCourseCommand(ProfessorService profesorService) {
		this.profesorService = profesorService;
	}

	public void setProfessorService(ProfessorService profesorService) {
		this.profesorService = profesorService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Professor> professors = profesorService.findAll();
		List<LevelDTO> levels = getLevels();

		request.setAttribute("professors", professors);
		request.setAttribute("levels", levels);

		return VIEW;
	}

	private List<LevelDTO> getLevels() {
		List<LevelDTO> result = new ArrayList<>();
		
		for (CourseLevel level : CourseLevel.values()) {
			result.add(new LevelDTO(level.getId(), level.getText()));
		}
		
		return result;
	}

}
