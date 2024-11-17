package com.iotassistant.validators;

public enum ValidationError {
	NO_ERROR(""),
	ENTITY_ALREADY_EXIST("%s already exist"),
	ENTITY_NOT_FOUND("%s not found"),
	ENTITY_HAS_NOT_PROPERTIES("%s has not properties"),
	ENTITY_HAS_NOT_PROPERTY("%s has not property"),
	DEVICE_HAS_NOT_WATCHDOG("%s has not watchdog"),
	ENTITY_VALUE_IS_NOT_VALID("%s invalid value"),
	INVALID_GPS_POSITION("Invalid GPS position");
	
	private String errorMsg;

	private ValidationError(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean hasErrors() {
		return this != NO_ERROR;
	}
	
	public void formatMsg(String string) {
		this.errorMsg = String.format(errorMsg, string);
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	

}
