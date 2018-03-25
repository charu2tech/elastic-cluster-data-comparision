package com.elastic.constants;

public enum HttpConstants {
	UTF8("UTF-8"),	
	URI_SEPERATOR("/"),
	PROTOCOL_URL_SEPERATOR("://"),
	HTTP("http"),
	HTTPS("https");
	
	private String name;

	private HttpConstants(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
