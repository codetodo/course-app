package com.codetodo.courseapp.bean.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonBeanDefinitionsLoader implements BeanDefinitionsLoader {

	private static final Logger LOGGER = Logger.getLogger(JsonBeanDefinitionsLoader.class.getName());

	public static final String DEFAULT_CONFIG_FILE_NAME = "bean-defs.json";

	private String fileName = DEFAULT_CONFIG_FILE_NAME;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public Map<String, BeanDef> load() {
		try {
			File beanDefsFile = getBeanDefsFile();
			JsonBeanDefinitions defsWrapper = new ObjectMapper().readValue(beanDefsFile, JsonBeanDefinitions.class);
			
			return defsWrapper.defs;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed loading json configuration file", e);
			throw new BeanCreationException("Failed loading json configuration file", e);
		}
	}

	private File getBeanDefsFile() throws FileNotFoundException, UnsupportedEncodingException {
		ClassLoader classLoader = getClass().getClassLoader();
		
		
		URL url = classLoader.getResource(fileName);
		if (url == null) {
			throw new FileNotFoundException(
					String.format("Configuratio File '%s' does not exist in classpath", fileName));
		}

		return new File(URLDecoder.decode(url.getFile(), "UTF-8"));
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
