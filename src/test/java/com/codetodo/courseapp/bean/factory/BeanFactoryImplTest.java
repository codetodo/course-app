package com.codetodo.courseapp.bean.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class BeanFactoryImplTest {

	private BeanFactory factory;

	@Before
	public void setUp()
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field instance = BeanFactoryImpl.class.getDeclaredField("instance");
		instance.setAccessible(true);
		instance.set(null, null);
	}

	@Test
	public void shouldCreateABean() {
		BeanDefinitionsLoader beanDefsLoader = mock(BeanDefinitionsLoader.class);
		Map<String, BeanDef> defs = getBeanDefs();
		Optional<String> beanNameOptional = defs.keySet().stream().findFirst();
		when(beanDefsLoader.load()).thenReturn(defs);
		factory = BeanFactoryImpl.getInstance(beanDefsLoader);

		Object instance = factory.getBean(beanNameOptional.get());

		assertNotNull(instance);
	}

	@Test
	public void shouldCreateOnlyOneInstanceOfBeanSetUpAsSingleton() {
		BeanDefinitionsLoader beanDefsLoader = mock(BeanDefinitionsLoader.class);
		Map<String, BeanDef> defs = getBeanDefs();
		Optional<Entry<String, BeanDef>> singletonBean = defs.entrySet().stream()
				.filter((e) -> e.getValue().isSingleton()).findFirst();
		when(beanDefsLoader.load()).thenReturn(defs);
		factory = BeanFactoryImpl.getInstance(beanDefsLoader);

		Object instance1 = factory.getBean(singletonBean.get().getKey());
		Object instance2 = factory.getBean(singletonBean.get().getKey());

		assertEquals(instance1, instance2);
	}

	private Map<String, BeanDef> getBeanDefs() {
		Map<String, BeanDef> defs = new HashMap<>();

		BeanDef courseDAODef = new BeanDef();
		courseDAODef.setBeanClass("com.codetodo.courseapp.dao.course.impl.JDBCCourseDAO");
		courseDAODef.setSingleton(true);

		BeanDef courseServiceDef = new BeanDef();
		courseServiceDef.setBeanClass("com.codetodo.courseapp.service.course.impl.CourseServiceImpl");
		courseServiceDef.setSingleton(true);

		List<PropertyDef> courseServiceProperties = new ArrayList<>();
		courseServiceProperties.add(new PropertyDef("courseDAO", "courseDAO"));

		courseServiceDef.setProperties(courseServiceProperties);

		defs.put("courseService", courseServiceDef);
		defs.put("courseDAO", courseDAODef);

		return defs;
	}

}
