package com.elastic.response.handler;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.util.StringUtil;

public class JsonResponseHandler extends BaseResponseHandler<JSONArray>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonResponseHandler.class);

	@Override
	public JSONArray handleResponse(final HttpResponse httpResponse){
		LOGGER.debug("Extracting json from response. httpResponse={}",httpResponse);
		HttpEntity httpEntity=getHttpEntityFromResponse(httpResponse);
		JSONArray response =null;
		try{
			if(httpEntity!=null){
				String responseString=EntityUtils.toString(httpEntity ,"UTF-8");
				responseString=StringUtil.trimIfNotNull(responseString);
				if(responseString!=null){
					JSONParser jsonParser = new JSONParser();
					response=(JSONArray)jsonParser.parse(responseString);
				}
			}
		}catch(IOException| org.apache.http.ParseException| org.json.simple.parser.ParseException  exception){
			LOGGER.error("Unable to convert http client get response to json.",exception);
		}
		return response;
	}	
}
