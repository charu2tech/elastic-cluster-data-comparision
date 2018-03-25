package com.elastic.pojo;

public class ClusterDetails {
	
	private String sourceClusterLbUrl;
	private String destinationClusterLbUrl;
	public ClusterDetails(){}
	public ClusterDetails(String sourceClusterLbUrl, String destinationClusterLbUrl) {
		super();
		this.sourceClusterLbUrl = sourceClusterLbUrl;
		this.destinationClusterLbUrl = destinationClusterLbUrl;
	}
	public String getSourceClusterLbUrl() {
		return sourceClusterLbUrl;
	}
	public void setSourceClusterLbUrl(String sourceClusterLbUrl) {
		this.sourceClusterLbUrl = sourceClusterLbUrl;
	}
	public String getDestinationClusterLbUrl() {
		return destinationClusterLbUrl;
	}
	public void setDestinationClusterLbUrl(String destinationClusterLbUrl) {
		this.destinationClusterLbUrl = destinationClusterLbUrl;
	}
	
}
