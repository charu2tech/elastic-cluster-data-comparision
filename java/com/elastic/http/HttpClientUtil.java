package com.elastic.http;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.response.handler.ResponseHandler;

public class HttpClientUtil<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
	
	private final CloseableHttpClient httpClient;
	
	public HttpClientUtil(final HttpClient httpClient) {
		this.httpClient = httpClient.getClient();
	}

	public CloseableHttpResponse executeGet(final String url){
		LOGGER.debug("Executing get request. url={}",url);
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response =null;
		try {
			response = this.httpClient.execute(request);
		} catch (IOException e) {
			LOGGER.error("Unable to execute get request. url={}", url);
		}
		return response;		
	}
	
	public T executeGet(String url, ResponseHandler<T> handler){
		LOGGER.debug("Executing get request. url={}, handler={}",url, handler);
		CloseableHttpResponse response = executeGet(url);
		T data =handler.handleResponse(response);
		closeCloseableHttpResponse(response);		
		return data;		
	}
	
	public static void closeCloseableHttpResponse(final CloseableHttpResponse response){
		try {
			response.close();
		} catch (IOException exception) {
			LOGGER.error("Unable to close http response object", exception);
		}
	}
}
