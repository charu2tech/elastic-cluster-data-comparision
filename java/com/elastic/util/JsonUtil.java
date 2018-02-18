package com.elastic.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonUtil {
	
	public static JSONObject getFirstElementFromArray(JSONArray jsonArray){
		JSONObject jsonObject=null;
		if(jsonArray!=null && jsonArray.size()>=1){
			jsonObject=(JSONObject)jsonArray.get(0);
		}
		return jsonObject;
		
	}

}
