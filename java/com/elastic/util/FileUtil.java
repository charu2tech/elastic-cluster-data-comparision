package com.elastic.util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	public static Properties getProperties(String filePath){
		Properties configProperties = new Properties();
		 try (InputStream  input =new FileInputStream(filePath)){
		configProperties.load(input);
		}catch(IOException ioException){
			LOGGER.error("Unable to load properties file. filePath={}",filePath);
		}
		return configProperties;
	}
	
	public static Object readJsonFile(String filePath){
		Object object=null;
        JSONParser jsonParser = new JSONParser();         
        try (FileReader reader = new FileReader(filePath))
        {
        	object = jsonParser.parse(reader); 
        } catch (IOException| ParseException e) {
        	LOGGER.error("Unable to read and parse json file. filePath={}",filePath);
        }
		return object;
	}

}
