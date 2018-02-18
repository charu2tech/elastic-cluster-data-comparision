package com.elastic.constants;

public enum HttpVerb {
	
	HEAD("HEAD"),
	GET("GET"),
	PUT("PUT"),
	POST("POST"),
	DELETE("DELETE");	
	
	private String verbName;

	private HttpVerb(String verbName) {
		this.verbName = verbName;
	}

	public String getVerbName() {
		return verbName;
	}
	
	

}
