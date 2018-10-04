package com.codetodo.courseapp.bean.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PropertyDefTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsRefType() {
		String name = "bean";
		Object value = "bar";
		
		PropertyDef propertyDef = new PropertyDef(name, value);
		
		boolean result = propertyDef.isRefType();
		
		assertEquals(name, propertyDef.getName());
		assertEquals(value, propertyDef.getValue());
		assertFalse(result);
	}

}
