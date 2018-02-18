package com.elastic.response.handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public interface ResponseHandler<T> {

	public T handleResponse(final HttpResponse httpResponse);
	public HttpEntity getHttpEntityFromResponse(final HttpResponse httpResponse);
	public Integer getHttpStatusFromResponse(final HttpResponse httpResponse);
}
