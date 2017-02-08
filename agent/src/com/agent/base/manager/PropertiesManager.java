package com.agent.base.manager;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertiesManager {
	@Autowired
	private static Properties propertiesReader;

	public static String get(String key){
		return propertiesReader.getProperty(key, null);
	}
	
	public static void setPropertiesReader(Properties propertiesReader) {
		PropertiesManager.propertiesReader = propertiesReader;
	}

}
