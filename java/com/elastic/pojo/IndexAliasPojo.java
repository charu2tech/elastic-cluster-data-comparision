package com.elastic.pojo;

public class IndexAliasPojo {
	
	private String alias;
	private String index;
	public IndexAliasPojo(String alias, String index) {
		super();
		this.alias = alias;
		this.index = index;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	

}
