package com.elastic.pojo;

public class ResponseEntity<T> implements Cloneable{
	private String elasticApiUrl;
	private T data;	
	
	public ResponseEntity() {
	}
	
	public String getElasticApiUrl() {
		return elasticApiUrl;
	}

	public void setElasticApiUrl(String elasticApiUrl) {
		this.elasticApiUrl = elasticApiUrl;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public ResponseEntity<T> buildElasticApiUrl(String elasticApiUrl){
		this.setElasticApiUrl(elasticApiUrl);
		return this;
	}
	
	public ResponseEntity<T> buildData(T data){
		this.setData(data);
		return this;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}