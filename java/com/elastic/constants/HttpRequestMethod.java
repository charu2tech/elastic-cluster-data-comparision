package com.elastic.constants;

public enum HttpRequestMethod {
	
	HEAD("HEAD"),
	GET("GET"),
	PUT("PUT"),
	POST("POST"),
	DELETE("DELETE");	
	
	private String verbName;

	private HttpRequestMethod(String verbName) {
		this.verbName = verbName;
	}

	public String getVerbName() {
		return verbName;
	}
	
	

}
