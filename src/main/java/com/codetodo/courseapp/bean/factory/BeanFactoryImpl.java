package com.codetodo.courseapp.bean.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;

public class BeanFactoryImpl implements BeanFactory {

	private static final Logger LOGGER = Logger.getLogger(BeanFactoryImpl.class.getName());

	private static BeanFactory instance = null;

	private Map<String, Object> singlentonBeans = new HashMap<>();

	private Map<String, BeanDef> beanDefs = new HashMap<>();

	private BeanFactoryImpl(BeanDefinitionsLoader beanDefsLoader) {
		Objects.requireNonNull(beanDefsLoader);
		beanDefs = beanDefsLoader.load();
	}

	@Override
	public Object getBean(String beanName) {
		try {
			return createBeanAux(beanName);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed bean creation", e);
			throw new BeanCreationException("Failed bean creation: " + beanName, e);
		}
	}

	private Object createBeanAux(String name)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Object bean = singlentonBeans.get(name);

		if (bean != null) {
			return bean;
		}

		BeanDef beanDef = beanDefs.get(name);

		if (beanDef == null) {
			throw new IllegalStateException("There is no bean register with name '" + name + "'");
		}

		Class<?> beanClass = Class.forName(beanDef.getBeanClass());
		Object beanInstance = beanClass.newInstance();

		for (PropertyDef propertyDef : beanDef.getProperties()) {
			Object propertyValue = null;
			if (propertyDef.isRefType()) {
				propertyValue = createBeanAux(propertyDef.getName());
			} else {
				propertyValue = propertyDef.getValue();
			}
			if (propertyValue != null) {
				setProperty(beanInstance, propertyDef.getName(), propertyValue);
			}
		}

		if (beanDef.isSingleton()) {
			singlentonBeans.put(name, beanInstance);
		}		

		return beanInstance;
	}

	private void setProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		BeanUtils.setProperty(bean, name, value);
	}

	public static BeanFactory getInstance() {
		return getInstance(new JsonBeanDefinitionsLoader());
	}
	
	public static BeanFactory getInstance(BeanDefinitionsLoader beanDefsLoader) {
		if (instance == null) {
			instance = new BeanFactoryImpl(beanDefsLoader);
		}
		return instance;
	}

}
