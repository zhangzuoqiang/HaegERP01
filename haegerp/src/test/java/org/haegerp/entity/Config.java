package org.haegerp.entity;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	
	private static Properties properties = new Properties();
	
	public static String getProperty(String property) throws Exception
	{
		if (properties.size() == 0)
			properties.load(new FileInputStream("./config.properties"));
		
		return properties.getProperty(property);
	}
}
