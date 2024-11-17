package com.iotassistant.validators;

import com.iotassistant.models.HttpDeviceInterface;
import com.iotassistant.models.devices.CameraInterface;
import com.iotassistant.models.devices.DeviceInterfaceVisitor;
import com.iotassistant.models.devices.GpsInterface;
import com.iotassistant.models.devices.transductors.SensorInterface;
import com.iotassistant.mqtt.MqttDeviceInterface;
import com.iotassistant.services.DevicesFacadeService;
import com.iotassistant.ttn.TTNDeviceInterface;


public class DeviceInterfaceValidator implements DeviceInterfaceVisitor {
	
	ValidationError validationError;
	
	public DeviceInterfaceValidator() {
		super();
		this.validationError = ValidationError.NO_ERROR;
	}

	public ValidationError validateNew(SensorInterface sensorInterface) {
		sensorInterface.accept(this);
		return this.validationError;
	}
	

	@Override
	public void visit(MqttDeviceInterface mqttInterface) {
		// Nothing to do as topic == name and uniques names were already checked 	
	}

	@Override
	public void visit(HttpDeviceInterface cameraHttpInterface) {
		if (DevicesFacadeService.getInstance().getHttpDeviceByUrl(cameraHttpInterface.getUrl()) != null){
			validationError = ValidationError.ENTITY_ALREADY_EXIST;
		}
		
	}

	@Override
	public void visit(TTNDeviceInterface ttnSensorInterface) {
		if (DevicesFacadeService.getInstance().getTTNDeviceByEndDeviceId(ttnSensorInterface.getDeviceId()) != null){
			validationError = ValidationError.ENTITY_ALREADY_EXIST;
		}
		
	}

	public ValidationError validateNew(CameraInterface cameraInterface) {
		cameraInterface.accept(this);
		return this.validationError;
	}

	public ValidationError validateNew(GpsInterface gpsInterface) {
		gpsInterface.accept(this);
		return this.validationError;
	}


}
