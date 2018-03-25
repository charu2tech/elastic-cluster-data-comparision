package com.elastic.response.handler;

import org.apache.http.HttpResponse;

public class StringResponseHandler extends BaseResponseHandler<String>{
	
	@Override
	public String handleResponse(final HttpResponse httpResponse) {
		return getResponse(httpResponse);
	}
}
