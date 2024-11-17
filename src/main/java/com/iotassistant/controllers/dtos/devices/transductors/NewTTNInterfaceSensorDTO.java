package com.iotassistant.controllers.dtos.devices.transductors;

import java.util.List;

import com.iotassistant.models.devices.WatchdogInterval;
import com.iotassistant.models.devices.transductors.Sensor;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.ttn.TTNSensorInterface;

public class NewTTNInterfaceSensorDTO extends NewTTNDeviceRequestDTO{
	
	private List<PropertyMeasuredEnum> propertiesMeasured;

	public List<PropertyMeasuredEnum> getPropertiesMeasured() {
		return propertiesMeasured;
	}
	

	public void setPropertiesMeasured(List<PropertyMeasuredEnum> propertiesMeasured) {
		this.propertiesMeasured = propertiesMeasured;
	}

	public Sensor getSensor() {
		TTNSensorInterface sensorTTNInterface = new TTNSensorInterface(this.getDeviceId());
		return new Sensor(super.getName(), super.getDescription(), propertiesMeasured, sensorTTNInterface, WatchdogInterval.getInstance(super.getWatchdogInterval()));	
	}
	
	

}
