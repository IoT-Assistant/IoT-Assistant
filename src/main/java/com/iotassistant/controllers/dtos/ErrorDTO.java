package com.iotassistant.controllers.dtos;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iotassistant.validators.ValidationError;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorDTO {
	ENTITY_ALREADY_EXIST(ValidationError.ENTITY_ALREADY_EXIST, HttpStatus.UNPROCESSABLE_ENTITY),
	ENTITY_NOT_FOUND(ValidationError.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND),
	ENTITY_HAS_NOT_PROPERTIES(ValidationError.ENTITY_HAS_NOT_PROPERTIES, HttpStatus.UNPROCESSABLE_ENTITY),
	ENTITY_HAS_NOT_PROPERTY(ValidationError.ENTITY_HAS_NOT_PROPERTY, HttpStatus.UNPROCESSABLE_ENTITY),
	DEVICE_HAS_NOT_WATCHDOG(ValidationError.DEVICE_HAS_NOT_WATCHDOG, HttpStatus.UNPROCESSABLE_ENTITY),
	ENTITY_VALUE_IS_NOT_VALID(ValidationError.ENTITY_VALUE_IS_NOT_VALID, HttpStatus.UNPROCESSABLE_ENTITY),
	INVALID_GPS_POSITION(ValidationError.INVALID_GPS_POSITION, HttpStatus.UNPROCESSABLE_ENTITY);
	
	private ValidationError validationError;
	
	private final HttpStatus httpStatus;
	
	private String errorMsg;

	private ErrorDTO(ValidationError validationError,  HttpStatus httpStatus) {
		this.validationError = validationError;
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ValidationError getValidationError() {
		return validationError;
	}

	public ResponseEntity<?> getResponseEntity() {
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", this.errorMsg);
		return new ResponseEntity<>(body, this.getHttpStatus());
	}

	public static ErrorDTO getInstance(ValidationError validationError) {
		for (ErrorDTO errorDTO : ErrorDTO.values()) {
			if (errorDTO.getValidationError().equals(validationError)) {
				errorDTO.setErrorMsg(validationError.getErrorMsg());
				return errorDTO;
			}
		}
		return null;
	}

	void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
		
	}
	
	
	

}
