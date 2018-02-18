package com.elastic.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndexDetailsPojo {
	
	private String pri;
	private String rep;
	private String status;
	private String index;
	@JsonProperty("docs.count")
	private String docsCount;
	public String getPri() {
		return pri;
	}
	public void setPri(String pri) {
		this.pri = pri;
	}
	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDocsCount() {
		return docsCount;
	}
	public void setDocsCount(String docsCount) {
		this.docsCount = docsCount;
	}
	
	

}
