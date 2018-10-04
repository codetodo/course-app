package com.codetodo.courseapp.bean.factory;

public class PropertyDef {
	private String name;
	private Object value;
	private String refBean;

	public PropertyDef() {

	}

	public PropertyDef(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public PropertyDef(String name, String refBean) {
		this.name = name;
		this.refBean = refBean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getRefBean() {
		return refBean;
	}

	public void setRefBean(String refBean) {
		this.refBean = refBean;
	}

	public boolean isRefType() {
		return refBean != null;
	}

}
