package com.codetodo.courseapp.controller.command.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.Validate;

import com.codetodo.courseapp.controller.command.Command;
import com.codetodo.courseapp.dto.course.LevelDTO;
import com.codetodo.courseapp.model.Professor;
import com.codetodo.courseapp.model.Course.CourseLevel;
import com.codetodo.courseapp.service.professor.ProfessorService;

public class AddCourseCommand implements Command {
	
    public static final String NAME = "addCourseCommand";

	public static final String VIEW = "views/course/add.jsp";
	
	public static final String PROFESSORS_ATTR_NAME = "professors";
	public static final String LEVELS_ATTR_NAME = "levels";

	private ProfessorService professorService;

	public AddCourseCommand() {
	}

	public AddCourseCommand(ProfessorService professorService) {
		this.professorService = professorService;
	}

	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Validate.notNull(professorService, "'professorService' is required");
		
		List<Professor> professors = professorService.findAll();
		List<LevelDTO> levels = getLevels();

		request.setAttribute(PROFESSORS_ATTR_NAME, professors);
		request.setAttribute(LEVELS_ATTR_NAME, levels);

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
