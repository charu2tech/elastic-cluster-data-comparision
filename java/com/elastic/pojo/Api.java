package com.elastic.pojo;

public class Api implements Comparable<Api>{
	
	private String apiName;
	private String apiDescription;
	private String apiPath;
	private String requestMethod;
	private ApiType apiType;
	private int apiExecutionOrder;
	public Api() {
	}

	public Api(String apiName, String apiDescription, String apiPath, String requestMethod, ApiType apiType,
			int apiExecutionOrder) {
		super();
		this.apiName = apiName;
		this.apiDescription = apiDescription;
		this.apiPath = apiPath;
		this.requestMethod = requestMethod;
		this.apiType = apiType;
		this.apiExecutionOrder = apiExecutionOrder;
	}

	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiDescription() {
		return apiDescription;
	}
	public void setApiDescription(String apiDescription) {
		this.apiDescription = apiDescription;
	}
	public String getApiPath() {
		return apiPath;
	}
	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public ApiType getApiType() {
		return apiType;
	}
	public void setApiType(ApiType apiType) {
		this.apiType = apiType;
	}
	public int getApiExecutionOrder() {
		return apiExecutionOrder;
	}
	public void setApiExecutionOrder(int apiExecutionOrder) {
		this.apiExecutionOrder = apiExecutionOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiName == null) ? 0 : apiName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Api other = (Api) obj;
		if (apiName == null) {
			if (other.apiName != null)
				return false;
		} else if (!apiName.equals(other.apiName))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Api [apiName=" + apiName + ", apiDescription=" + apiDescription + ", apiPath=" + apiPath
				+ ", requestMethod=" + requestMethod + ", apiType=" + apiType + ", apiExecutionOrder="
				+ apiExecutionOrder + "]";
	}

	@Override
	public int compareTo(Api api) {
		return this.apiExecutionOrder-api.apiExecutionOrder;
	}	

}
