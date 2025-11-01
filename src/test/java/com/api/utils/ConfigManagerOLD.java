package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
//WAP to read the Properties file from src/test/resources/config/config.properties
	
	private static Properties prop = new Properties();
	
	static {
		//Operation of loading the file in the memory
		//static block it will be executed Once During Class Load Time
		File configFile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  static String getProperty(String Key) {
		
		
		
		
		
		return prop.getProperty(Key);
		
	}
}

