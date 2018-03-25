package com.elastic.pojo;

public class ApiHandlerMapper<E> {
	
	private Api api;	
	private String validaterClass;
	private String responseHandlerClass;
	private ApiValidationMessages apiValidationMessages;
	
	public ApiHandlerMapper() {
		super();
	}

	public ApiHandlerMapper(Api api, String validaterClass, String responseHandlerClass,
			ApiValidationMessages apiValidationMessages) {
		super();
		this.api = api;
		this.validaterClass = validaterClass;
		this.responseHandlerClass = responseHandlerClass;
		this.apiValidationMessages = apiValidationMessages;
	}

	public Api getApi() {
		return api;
	}

	public void setApi(Api api) {
		this.api = api;
	}

	public String getValidaterClass() {
		return validaterClass;
	}

	public void setValidaterClass(String validaterClass) {
		this.validaterClass = validaterClass;
	}

	public String getResponseHandlerClass() {
		return responseHandlerClass;
	}

	public void setResponseHandlerClass(String responseHandlerClass) {
		this.responseHandlerClass = responseHandlerClass;
	}

	public ApiValidationMessages getApiValidationMessages() {
		return apiValidationMessages;
	}

	public void setApiValidationMessages(ApiValidationMessages apiValidationMessages) {
		this.apiValidationMessages = apiValidationMessages;
	}

	@Override
	public String toString() {
		return "ApiHandlerMapper [api=" + api + ", validaterClass=" + validaterClass + ", responseHandlerClass="
				+ responseHandlerClass + ", apiValidationMessages=" + apiValidationMessages + "]";
	}

	

}
