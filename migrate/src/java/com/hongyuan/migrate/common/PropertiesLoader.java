package com.hongyuan.migrate.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
	public static Properties env=new Properties();
	
	static{
		InputStream in=PropertiesLoader.class.getClassLoader().getResourceAsStream("env.properties");
		try {
			env.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
