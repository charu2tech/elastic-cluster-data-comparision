package com.elastic.response.handler;

import org.apache.http.HttpResponse;

public class StatusResponseHandler extends BaseResponseHandler<Integer>{

	@Override
	public Integer handleResponse(HttpResponse httpResponse) {
		return getHttpStatusFromResponse(httpResponse);
	}
}
