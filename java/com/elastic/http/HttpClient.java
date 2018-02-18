package com.elastic.http;

import java.io.IOException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

	private final CloseableHttpClient httpClient;

	public HttpClient(){
		httpClient=HttpClients.createDefault();
		LOGGER.info("HttpClient object is created.");
	}
	
	public CloseableHttpClient getClient(){
		return this.httpClient;
	}
	
	public void closeClient(){
		try {
			httpClient.close();
		}catch (IOException exception) {
			LOGGER.error("Unable to close http client connection", exception);
        }
	}
	
}
