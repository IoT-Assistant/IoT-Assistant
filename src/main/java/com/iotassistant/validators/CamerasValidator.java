package com.iotassistant.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.devices.Camera;
import com.iotassistant.models.devices.CameraInterface;
import com.iotassistant.services.CamerasService;

@Component
public class CamerasValidator extends DevicesValidator{
	
	@Autowired
	private CamerasService camerasService;
	
	private static String deviceType = "Camera";
	
	public ValidationError validateNew(Camera newCamera) {
		ValidationError error = super.validateNew(camerasService, newCamera.getName());
		if (!error.hasErrors()) {
			error = new DeviceInterfaceValidator().validateNew((CameraInterface) newCamera.getInterface());
		}
		return this.format(error, deviceType, newCamera.getName());
	}


	public ValidationError validateDelete(String cameraName) {
		ValidationError error = super.validateDelete(camerasService, cameraName);
		return this.format(error, deviceType, cameraName);
	}
	
	public ValidationError validateEnableDisableWatchdog(String cameraName) {
		ValidationError error = super.validateEnableDisableWatchdog(camerasService, cameraName);
		return this.format(error, deviceType, cameraName);
	}

	public ValidationError validateGet(String cameraName) {
		ValidationError error = super.validateGet(camerasService, cameraName);
		return this.format(error, deviceType, cameraName);
	}

}
