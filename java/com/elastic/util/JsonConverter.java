package com.elastic.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
	
	public static <T> T toObject(JSONObject jsonObject, Class classObj){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(
			    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T object = null;
		try{
		object=(T)objectMapper.readValue(jsonObject.toJSONString(), classObj);
		}catch(IOException exception){
			System.out.println("Unable to convert given json to object. class name="+classObj.getName()+" json string="+jsonObject.toJSONString());
			exception.printStackTrace();
		}
		return object;
	}
	
	public static <T> List<T> toList(JSONArray jsonArray, Class classObj) {
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.configure(
				    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   Class<T[]> arrayClass = null;
		   try{
		   arrayClass=(Class<T[]>) Class.forName("[L" + classObj.getName() + ";");
		   }catch(ClassNotFoundException exception){
				System.out.println("Unable to convert given json to list. class name="+classObj.getName()+" json string="+jsonArray.toJSONString());
				exception.printStackTrace();
			}
		   T[] objects = null;
		   try{
		   objects=mapper.readValue(jsonArray.toJSONString(), arrayClass);
		   }catch(IOException exception){
				System.out.println("Unable to convert given json to list. class name="+classObj.getName()+" json string="+jsonArray.toJSONString());
				exception.printStackTrace();
			}
		   return Arrays.asList(objects);
	}

}
