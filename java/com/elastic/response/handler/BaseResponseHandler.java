package com.elastic.response.handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseResponseHandler<T> implements ResponseHandler<T>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseResponseHandler.class);
	
	@Override
	public HttpEntity getHttpEntityFromResponse(final HttpResponse httpResponse) {
		LOGGER.debug("Extracting http entity from response");
		HttpEntity entity =null;
		int status = getHttpStatusFromResponse(httpResponse);
		if(status==-1){
			return entity;
		}else if (status >= 200 && status < 300) {
			entity = httpResponse.getEntity();
		}else{
			LOGGER.error("Unexpected response status. status={}",status);
		}
		return entity;
	}

	@Override
	public Integer getHttpStatusFromResponse(final HttpResponse httpResponse) {
		LOGGER.debug("Extracting status code from response. httpResponse={}",httpResponse);
		int status = -1;
		if(httpResponse!=null && httpResponse.getStatusLine()!=null){
			status=httpResponse.getStatusLine().getStatusCode();
		}else{
			LOGGER.error("Unable to retrieve status code from response. either response or statu line objects are null.");
		}			
		return status;
	}	
	
}
