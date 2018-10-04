package com.codetodo.courseapp.bean.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BeanDefTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddPropertyDef() {
		BeanDef beanDef = new BeanDef();
		
		PropertyDef propertyDef = new PropertyDef("property1", "value1");
		beanDef.addPropertyDef(propertyDef );
		
		assertTrue(beanDef.getProperties().contains(propertyDef));
	}

}
