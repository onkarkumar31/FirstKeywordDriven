package com.mw.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	public static void main(String[] args) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/config.properties");
		System.out.println(System.getProperty("user.dir"));
		prop.load(fis);
		System.out.println(prop.get("browser"));
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
		prop.load(fis);
		System.out.println(prop.get("bmlBtn"));
		
	}

}
