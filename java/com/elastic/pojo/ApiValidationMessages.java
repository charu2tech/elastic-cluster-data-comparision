package com.elastic.pojo;

public class ApiValidationMessages {
	
	private String startMessage;
	private String successMessage;
	private String failureMessage;
	
	public ApiValidationMessages() {
	}
	public ApiValidationMessages(String startMessage, String successMessage, String failureMessage) {
		super();
		this.startMessage = startMessage;
		this.successMessage = successMessage;
		this.failureMessage = failureMessage;
	}
	public String getStartMessage() {
		return startMessage;
	}
	public void setStartMessage(String startMessage) {
		this.startMessage = startMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getFailureMessage() {
		return failureMessage;
	}
	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
	@Override
	public String toString() {
		return "ApiValidationMessages [startMessage=" + startMessage + ", successMessage=" + successMessage
				+ ", failureMessage=" + failureMessage + "]";
	}
	
	
	
	

}
