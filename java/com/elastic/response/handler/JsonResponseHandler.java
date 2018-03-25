package com.elastic.response.handler;

import org.apache.http.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonResponseHandler extends BaseResponseHandler<JSONObject>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonResponseHandler.class);

	@Override
	public JSONObject handleResponse(final HttpResponse httpResponse){
		JSONObject responseJson=null;
		String responseString = getResponse(httpResponse);
		if(responseString!=null){
			if(responseString.isEmpty()){
				responseJson=new JSONObject();
			}else{
				JSONParser jsonParser = new JSONParser();
				try {
					responseJson=(JSONObject)jsonParser.parse(responseString);
				} catch (ParseException exception) {
					LOGGER.error("Unable to convert http response to json.",exception);
				}
			}
		}
		return responseJson;
	}	
}
