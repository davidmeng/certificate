package mfw.acegi.dao.util;


import java.io.BufferedInputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.InputStream;

import java.util.Properties;


public class ConfigureValues
{
	private static String strFilePath = "/WEB-INF/classes/META-INF/configureValue.properties";

	
	public Properties getPropertiesObj() {
		Properties prop = null;
		
		prop = new Properties();
		try {
			File directory = new File("");
			System.out.println("123123131=" + directory.getAbsolutePath());
			InputStream is = new BufferedInputStream(new FileInputStream(
					"../webapps/certificate/WEB-INF/classes/META-INF/configureValue.properties"));
			if (is == null) {
				System.out.println("1");
				return null;
				}
			try {
				prop.load(is);
				} catch (IOException e) {
				System.out.println("2");
				return null;
				}
			try {
				is.close();
				} catch (IOException e) {
				System.out.println("3");
				return null;
				}
			return prop;
			} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			}
		return prop;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.util.ConfigureValues JD-Core Version: 0.6.2
 */