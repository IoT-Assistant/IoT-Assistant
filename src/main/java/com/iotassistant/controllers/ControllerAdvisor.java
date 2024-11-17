package com.iotassistant.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.iotassistant.models.devices.CameraInterfaceException;
import com.iotassistant.services.system.SystemCantShutdownException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler { 
	
	 @Override
	    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("message", "Invalid data");	       
	        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
	    }
		
	

	
	@ExceptionHandler({CameraInterfaceException.class})
    public ResponseEntity<Object> handleCameraInterfaceException(
    		CameraInterfaceException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", exception.getErrorMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
			
	
	@ExceptionHandler({SystemCantShutdownException.class})
    public ResponseEntity<Object> handleSystemCantShutdownException(
    		SystemCantShutdownException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "System can not power off because " + exception.getReason());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}
