package com.codetodo.courseapp.bean.factory;

import java.util.ArrayList;
import java.util.List;

public class BeanDef {
	private String beanClass;
	private boolean singleton = true;

	private List<PropertyDef> properties = new ArrayList<>();

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public List<PropertyDef> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyDef> properties) {
		this.properties = properties;
	}

	public void addPropertyDef(PropertyDef propertyDef) {
		properties.add(propertyDef);
	}

	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

}
