package com.elastic.constants;

public enum ElasticConstants {
	SOURCE_CLUSTER_URL("elastic.source.cluster.url"),
	DESTINATION_CLUSTER_URL("elastic.destination.cluster.url"),
	RED("red"),
	GREEN("green"),
	YELLOW("yellow");
	
	private String name;

	private ElasticConstants(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
