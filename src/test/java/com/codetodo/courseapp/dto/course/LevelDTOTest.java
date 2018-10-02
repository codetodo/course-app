package com.codetodo.courseapp.dto.course;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LevelDTOTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLevelDTOInitialization() {
		String id = "02";
		String text = "Basic";
		LevelDTO levelDTO = new LevelDTO(id, text);

		assertEquals(id, levelDTO.getId());
		assertEquals(text, levelDTO.getText());
	}

	@Test
	public void testLevelDTOSetters() {
		String id = "05";
		String text = "Advanced";

		LevelDTO levelDTO = new LevelDTO();

		levelDTO.setId(id);
		levelDTO.setText(text);

		assertEquals(id, levelDTO.getId());
		assertEquals(text, levelDTO.getText());
	}

}
