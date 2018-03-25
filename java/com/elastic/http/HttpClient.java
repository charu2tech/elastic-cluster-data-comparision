package com.elastic.http;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

	private static CloseableHttpClient closeableHttpClient=null;
	
	public static CloseableHttpClient getHttpClient(){
		if(closeableHttpClient==null){
			initializeHttpClient();
		}
		return closeableHttpClient;
	}
	
	private static synchronized void initializeHttpClient(){
		if(closeableHttpClient==null){
		PoolingHttpClientConnectionManager connectionManager= new PoolingHttpClientConnectionManager();
    	connectionManager.setMaxTotal(30);
    	connectionManager.setDefaultMaxPerRoute(10);
    	closeableHttpClient = HttpClients.custom()
		        .setConnectionManager(connectionManager)
		        .build();
    	LOGGER.info("Initialized http client(Elastic cluster data transfer/comparision util).");
		}
	}
	
	public static void closeHttpClient(){
		HttpClientUtils.closeQuietly(closeableHttpClient);
		LOGGER.info("Closed http client and all its resources(Elastic cluster data transfer/comparision util).");
	}

}