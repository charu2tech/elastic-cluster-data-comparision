package com.elastic.pojo;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.simple.JSONArray;

public class ResponseEntity {
	
	String lbUrl;
	String elasticApiUrl;
	CloseableHttpResponse closeableHttpResponse;
	JSONArray responseJsonArray;
	Integer statusCode;
	
	public ResponseEntity(String lbUrl, String elasticApiUrl, CloseableHttpResponse closeableHttpResponse,
			JSONArray responseJsonArray, Integer statusCode) {
		super();
		this.lbUrl = lbUrl;
		this.elasticApiUrl = elasticApiUrl;
		this.closeableHttpResponse = closeableHttpResponse;
		this.responseJsonArray = responseJsonArray;
		this.statusCode = statusCode;
	}
	public String getLbUrl() {
		return lbUrl;
	}
	public void setLbUrl(String lbUrl) {
		this.lbUrl = lbUrl;
	}
	public String getElasticApiUrl() {
		return elasticApiUrl;
	}
	public void setElasticApiUrl(String elasticApiUrl) {
		this.elasticApiUrl = elasticApiUrl;
	}
	public CloseableHttpResponse getCloseableHttpResponse() {
		return closeableHttpResponse;
	}
	public void setCloseableHttpResponse(CloseableHttpResponse closeableHttpResponse) {
		this.closeableHttpResponse = closeableHttpResponse;
	}
	public JSONArray getResponseJsonArray() {
		return responseJsonArray;
	}
	public void setResponseJsonArray(JSONArray responseJsonArray) {
		this.responseJsonArray = responseJsonArray;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	
	

}
