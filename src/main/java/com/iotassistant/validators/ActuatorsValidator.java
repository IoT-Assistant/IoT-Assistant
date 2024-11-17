package com.iotassistant.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.devices.transductors.Actuator;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;
import com.iotassistant.services.ActuatorsService;

@Component
public class ActuatorsValidator extends DevicesValidator{
	
	@Autowired
	private ActuatorsService actuatorsService;
	
	private static String deviceType = "Actuator";
	
	public ValidationError validateNew(Actuator newActuator) {
		ValidationError error = super.validateNew(actuatorsService, newActuator.getName());
		if (!error.hasErrors() && newActuator.getPropertiesActuated() == null || newActuator.getPropertiesActuated().isEmpty()) {
			return ValidationError.ENTITY_HAS_NOT_PROPERTIES;
		}
		return this.format(error, deviceType, newActuator.getName());
	}
	
	public ValidationError validateDelete(String actuatorName) {
		ValidationError error = super.validateDelete(actuatorsService, actuatorName);
		return this.format(error, deviceType, actuatorName);
	}
	
	public ValidationError validateEnableDisableWatchdog(String actuatorName) {
		ValidationError error = super.validateEnableDisableWatchdog(actuatorsService, actuatorName);
		return this.format(error, deviceType, actuatorName);
	}

	public ValidationError validateGet(String actuatorName) {
		ValidationError error = super.validateGet(actuatorsService, actuatorName);
		return this.format(error, deviceType, actuatorName);
	}

	public ValidationError validateSetValue(String actuatorName, String value, PropertyActuatedEnum propertyActuated) {
		ValidationError error = ValidationError.NO_ERROR;
		if (!actuatorsService.exist(actuatorName))  {
			error = ValidationError.ENTITY_NOT_FOUND;
	    }
		else if (!actuatorsService.hasActuatorProperty(actuatorName, propertyActuated)) {
			error = ValidationError.ENTITY_HAS_NOT_PROPERTY;
		}
		else if (!propertyActuated.isValidValue(value) ) {
			error = ValidationError.ENTITY_VALUE_IS_NOT_VALID;
		}
		return error;
	}

}

