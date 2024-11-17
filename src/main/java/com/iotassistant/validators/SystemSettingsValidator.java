package com.iotassistant.validators;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.SystemSettings;
import com.iotassistant.services.system.SystemService;

@Component
public class SystemSettingsValidator {
	
	@Autowired
	SystemService systemService;
	
	public ValidationError validateSet(SystemSettings settings) {
		ValidationError error = ValidationError.NO_ERROR;
		if (!settings.getSecurityUsername().equals(systemService.getSettings().getSecurityUsername())
				|| settings.getSecurityPassword() == null || settings.getSecurityPassword() == "") {
			error = ValidationError.ENTITY_VALUE_IS_NOT_VALID;
			error.formatMsg("Username/Password");
		}
		if (isNotUrl(settings.getMqttSettings().getBroker()) || isNotUrl(settings.getTtnSettings().getBroker())) {
			error = ValidationError.ENTITY_VALUE_IS_NOT_VALID;
			error.formatMsg("Broker");
		}
		return error;
	}

	private boolean isNotUrl(String broker) {
		try {
			new URL(broker);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

}
