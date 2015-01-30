package com.artica.telesalud.common.data;

public class DataConstraintViolation {
	private String field;
	private String messageKey;
	
	public DataConstraintViolation(String field, String messageKey) {
		this.field = field;
		this.messageKey = messageKey;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public String getField() {
		return field;
	}
	public String getMessageKey() {
		return messageKey;
	}
	

}
