package com.edu.demo1029;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class ReadPro {

	public static final String filePath="conf/config.properties";
	

	public static String getProValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		fis.close();
		return prop.getProperty(key);
		
	}
}
