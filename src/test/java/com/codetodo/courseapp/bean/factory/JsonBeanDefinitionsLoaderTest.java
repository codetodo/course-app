package com.codetodo.courseapp.bean.factory;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class JsonBeanDefinitionsLoaderTest {
	
	public static final String FILE_NAME = "test-bean-defs.json";

	private BeanDefinitionsLoader beanDefsLoader;
	
	@Before
	public void setUp() throws Exception {
		beanDefsLoader = new JsonBeanDefinitionsLoader();
	}

	@Test
	public void shouldLoadBeanDefinitionsFromJSONFile() {
		((JsonBeanDefinitionsLoader)beanDefsLoader).setFileName(FILE_NAME);
		
		Map<String, BeanDef> beanDefs = beanDefsLoader.load();
		
		assertNotNull(beanDefs);
		assertNotNull(beanDefs.get("courseService"));
	}

}
