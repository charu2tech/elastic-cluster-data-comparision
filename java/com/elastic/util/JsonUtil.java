package com.elastic.util;


import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	

	
	public static <T> T toObject(JSONObject jsonObject, Class<T> classObj){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(
			    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T object = null;
		try{
		object=(T)objectMapper.readValue(jsonObject.toJSONString(), classObj);
		}catch(IOException exception){
			LOGGER.error("Unable to convert given json to object. class name={} json string={}",classObj.getName(),jsonObject.toJSONString());
		}
		return object;
	}
	
	public static <T> List<T> toList(JSONArray jsonArray, Class<T> classObj) {
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   Class<T[]> arrayClass = null;
		   T[] objects = null;
		   try{
		   arrayClass=(Class<T[]>) Class.forName("[L" + classObj.getName() + ";");
		   objects=mapper.readValue(jsonArray.toJSONString(), arrayClass);
		   }catch(ClassNotFoundException|IOException exception){
			   LOGGER.error("Unable to convert given json to list. class name={} json string={}",classObj.getName(),jsonArray.toJSONString());
			}
		   return Arrays.asList(objects);
	}
	
	public static <T> List<T> readJsonAndConvertToObject(String filePath, Class<T> classObj){
		Object object= FileUtil.readJsonFile(filePath);
		List<T> list=null;
		if(object ==null){
			list= null;
		}else if(object instanceof JSONArray){
			list =toList((JSONArray)object, classObj);
		}else if(object instanceof JSONObject){
			list= Arrays.asList(toObject((JSONObject)object,classObj));
		}
		return list;
	}

}
