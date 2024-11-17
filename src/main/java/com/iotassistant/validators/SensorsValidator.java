package com.iotassistant.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.devices.transductors.Sensor;
import com.iotassistant.models.devices.transductors.SensorInterface;
import com.iotassistant.services.SensorsService;

@Component
public class SensorsValidator extends DevicesValidator{
	
	@Autowired
	private SensorsService sensorsService;

	
	private static String deviceType = "Sensor";
	
	public ValidationError validateNew(Sensor newSensor) {
		ValidationError error = super.validateNew(sensorsService, newSensor.getName());
		if (!error.hasErrors() && newSensor.getPropertiesMeasured() == null || newSensor.getPropertiesMeasured().isEmpty()) {
			error = ValidationError.ENTITY_HAS_NOT_PROPERTIES;
		}
		if (!error.hasErrors()) {
			error = new DeviceInterfaceValidator().validateNew((SensorInterface) newSensor.getInterface());
		}
		return this.format(error, deviceType, newSensor.getName());
	}
	


	public ValidationError validateDelete(String sensorName) {
		ValidationError error = super.validateDelete(sensorsService, sensorName);
		return this.format(error, deviceType, sensorName);
	}
	
	public ValidationError validateEnableDisableWatchdog(String sensorName) {
		ValidationError error = super.validateEnableDisableWatchdog(sensorsService, sensorName);
		return this.format(error, deviceType, sensorName);
	}

	public ValidationError validateGet(String sensorName) {
		ValidationError error = super.validateGet(sensorsService, sensorName);
		return this.format(error, deviceType, sensorName);
	}

}
