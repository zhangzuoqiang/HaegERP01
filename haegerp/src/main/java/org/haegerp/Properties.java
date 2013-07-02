package org.haegerp;

import java.io.FileInputStream;

public class Properties {
	
	private static java.util.Properties properties = new java.util.Properties();
	
	public static boolean loadProperties(){
		try {
			properties.load(new FileInputStream("./config.properties"));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static String getProperty(String property)
	{
		return properties.getProperty(property);
	}
}
