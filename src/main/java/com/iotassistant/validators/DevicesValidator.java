package com.iotassistant.validators;

import com.iotassistant.services.DeviceService;

abstract class DevicesValidator {
	
	protected ValidationError format(ValidationError error, String deviceType, String deviceName) {
		error.formatMsg(deviceType + " " + deviceName);
		return error;
	}
	
	protected ValidationError validateNew(DeviceService deviceService, String deviceName) {
		ValidationError error = ValidationError.NO_ERROR;
		if (deviceService.exist(deviceName))  {
			return ValidationError.ENTITY_ALREADY_EXIST;
	    }
		return error;
	}
	
	protected ValidationError validateDelete(DeviceService deviceService, String deviceName) {
		return this.validateExist(deviceService, deviceName);
	}
	
	private ValidationError validateExist(DeviceService deviceService, String deviceName) {
		ValidationError error = ValidationError.NO_ERROR;
		if (!deviceService.exist(deviceName))  {
			return ValidationError.ENTITY_NOT_FOUND;
	    }
		return error;
	}
	

	public ValidationError validateEnableDisableWatchdog(DeviceService deviceService, String deviceName) {
		ValidationError error = this.validateExist(deviceService, deviceName);
		if (!error.hasErrors() && !deviceService.getByName(deviceName).hasWatchdog())  {
			error = ValidationError.DEVICE_HAS_NOT_WATCHDOG;		
	    }
		return error;
	}

	public ValidationError validateGet(DeviceService deviceService, String deviceName) {
		return this.validateExist(deviceService, deviceName);
	}

}
