package com.elastic.constants;

public enum Elastic {

	URI_SEPERATOR("/"),
	QUERY_PARAM_PRETTY("pretty"),
	QUERY_PARAM_SEPERATOR("&"),
	QUERY_PARAM_JSON_FORMATER("format=json"),
	JSON_VALUE_RED("red"),
	JSON_VALUE_YELLOW("yellow"),
	JSON_VALUE_GREEN("green"),
	JSON_KEY_STATUS("status"),
	JSON_KEY_PRI("pri"),
	JSON_KEY_REP("rep"),
	JSON_KEY_INDEX("index"),
	JSON_KEY_DOCS_COUNT("docs.count"),
	JSON_VALUE_OPEN("open"),
	JSON_KEY_COUNT("count");
	
	private String name;

	private Elastic(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
}
