package com.codetodo.courseapp.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.codetodo.courseapp.model.Course.CourseLevel;

public class CourseTest {

	@Test
	public void testEquals() {
		Course course1 = new Course.Builder().setTitle("course1").setLevel(CourseLevel.BASIC).build();
		Course course2 = new Course.Builder().setTitle("course1").setLevel(CourseLevel.ADVANCED).build();
		
		assertTrue(course1.equals(course1));
		assertFalse(course1.equals(course2));
		assertEquals(course1.hashCode(), course1.hashCode());
	}

}
