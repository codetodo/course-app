package com.codetodo.courseapp.bean.factory;

import java.util.Map;

public interface BeanDefinitionsLoader {
	Map<String, BeanDef> load();
}
