package com.codetodo.courseapp.bean.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonBeanDefinitionsLoader implements BeanDefinitionsLoader {

	private static final Logger LOGGER = Logger.getLogger(JsonBeanDefinitionsLoader.class.getName());

	public static final String CONFIG_FILE_NAME = "bean-defs.json";

	@Override
	public Map<String, BeanDef> load() {
		try {
			File beanDefsFile = getBeanDefsFile();
			JsonBeanDefinitions defsWrapper = new ObjectMapper().readValue(beanDefsFile, JsonBeanDefinitions.class);
			
			return defsWrapper.defs;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed loading json configuration file", e);
			throw new RuntimeException("Failed loading json configuration file", e);
		}
	}

	private File getBeanDefsFile() throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();

		URL url = classLoader.getResource(CONFIG_FILE_NAME);
		if (url == null) {
			throw new FileNotFoundException(
					String.format("Configuratio File '%s' does not exist in classpath", CONFIG_FILE_NAME));
		}

		return new File(url.getFile());
	}

	public static class JsonBeanDefinitions {

		Map<String, BeanDef> defs;

		public Map<String, BeanDef> getDefs() {
			return defs;
		}

		public void setDefs(Map<String, BeanDef> defs) {
			this.defs = defs;
		}

	}

}
