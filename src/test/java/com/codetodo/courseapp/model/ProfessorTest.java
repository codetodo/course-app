package com.codetodo.courseapp.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInitProfesoor() {
		String name = "pedro";
		String email = "pepe@host.com";
		Long id = 1l;
		
		Professor professor = new Professor.Builder().setName(name ).setEmail(email).setId(id ).build();
		
		assertEquals(name, professor.getName());
		assertEquals(email, professor.getEmail());
		assertEquals(id, professor.getId());
	}
	
	@Test
	public void testProfessorEquals() {
		String name = "juan";
		String email = "juan@host.com";
		Long id = 2l;
		
		Professor professor1 = new Professor.Builder().setName(name ).setEmail(email).setId(id ).build();
		Professor professor2 = new Professor.Builder().setName(name ).setEmail(email).setId(id ).build();
		
		assertTrue(professor1.equals(professor1));
		assertTrue(professor1.equals(professor2));
	}
	
	@Test
	public void testHashCode() {
		String email = "name@host.com";
		
		Professor professor1 = new Professor.Builder().setEmail(email).build();
		Professor professor2 = new Professor.Builder().setEmail(email).build();
		
		assertEquals(professor1.hashCode(), professor2.hashCode());
	}

}
