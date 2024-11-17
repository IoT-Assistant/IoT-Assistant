package com.iotassistant.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.devices.Gps;
import com.iotassistant.models.devices.GpsInterface;
import com.iotassistant.services.GpsesService;

@Component
public class GpsesValidator extends DevicesValidator{
	
	@Autowired
	private GpsesService gpssService;
	
	private static String deviceType = "Gps";
	
	public ValidationError validateNew(Gps newGps) {
		ValidationError error = super.validateNew(gpssService, newGps.getName());
		if (!error.hasErrors()) {
			error = new DeviceInterfaceValidator().validateNew((GpsInterface) newGps.getInterface());
		}
		return this.format(error, deviceType, newGps.getName());
	}


	public ValidationError validateDelete(String gpsName) {
		ValidationError error = super.validateDelete(gpssService, gpsName);
		return this.format(error, deviceType, gpsName);
	}
	
	public ValidationError validateEnableDisableWatchdog(String gpsName) {
		ValidationError error = super.validateEnableDisableWatchdog(gpssService, gpsName);
		return this.format(error, deviceType, gpsName);
	}

	public ValidationError validateGet(String gpsName) {
		ValidationError error = super.validateGet(gpssService, gpsName);
		return this.format(error, deviceType, gpsName);
	}

}

