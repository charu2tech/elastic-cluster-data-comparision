package com.elastic.response.handler;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.util.StringUtil;

public class StringResponseHandler extends BaseResponseHandler<String>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StringResponseHandler.class);

	@Override
	public String handleResponse(final HttpResponse httpResponse) {
		LOGGER.debug("Extracting string from response. httpResponse={}",httpResponse);
		HttpEntity httpEntity=getHttpEntityFromResponse(httpResponse);
		String response =null;
		try{
			if(httpEntity!=null){
				response=EntityUtils.toString(httpEntity ,"UTF-8");
				response=StringUtil.trimIfNotNull(response);
			}
		}catch(IOException exception){
			LOGGER.error("Unable to convert http client get response to string.",exception);
		}
		return response;
	}
}
