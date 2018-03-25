package com.elastic.response.handler;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.util.HttpUtil;
import com.elastic.util.StringUtil;

public abstract class BaseResponseHandler<T> implements ResponseHandler<T>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseResponseHandler.class);
	
	@Override
	public HttpEntity getHttpEntityFromResponse(final HttpResponse httpResponse) {
		HttpEntity entity =null;
		int status = getHttpStatusFromResponse(httpResponse);
		if(status==-1){
			return entity;
		}else if(status >= 200 && status < 300){
			entity = httpResponse.getEntity();		
		}else{
			LOGGER.error("Unexpected http response status. status={}, status line={}",status, httpResponse.getStatusLine());
		}
		return entity;
	}

	@Override
	public Integer getHttpStatusFromResponse(final HttpResponse httpResponse) {
		int status = -1;
		if(httpResponse!=null && httpResponse.getStatusLine()!=null){
			status=httpResponse.getStatusLine().getStatusCode();
		}else{
			LOGGER.error("Unable to retrieve http status code from response. either response or status line object is null.");
		}			
		return status;
	}
	
	protected String getResponse(final HttpResponse httpResponse){
		HttpEntity httpEntity=getHttpEntityFromResponse(httpResponse);
		String response =null;
		try{
			if(httpEntity!=null){
				response=EntityUtils.toString(httpEntity,HttpUtil.getEncoding(httpEntity.getContentEncoding()));
				response=StringUtil.trimIfNotNullOrEmpty(response);
			}
		}catch(IOException exception){
			LOGGER.error("Unable to convert http response to string.",exception);
		}
		return response;
	}
	
}
