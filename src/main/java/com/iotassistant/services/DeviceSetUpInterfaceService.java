package com.iotassistant.services;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iotassistant.models.HttpDeviceInterface;
import com.iotassistant.models.devices.DeviceInterface;
import com.iotassistant.models.devices.DeviceInterfaceVisitor;
import com.iotassistant.mqtt.MqttDeviceInterface;
import com.iotassistant.mqtt.MqttDevicesController;
import com.iotassistant.ttn.TTNDeviceInterface;
import com.iotassistant.ttn.TTNDevicesController;


class DeviceSetUpInterfaceService implements DeviceInterfaceVisitor {

	Logger logger = LoggerFactory.getLogger(DeviceSetUpInterfaceService.class);
	
	public DeviceSetUpInterfaceService() {
		super();
	}

	public void setUp(DeviceInterface deviceInterface) {
		deviceInterface.accept(this);	
	}
	
	@Override
	public void visit(MqttDeviceInterface mqttInterface) {
		try {
			MqttDevicesController.getInstance().subscribe(mqttInterface);
		} catch (MqttException e) {
			logger.error(e.getMessage());
		} 	
	}




	@Override
	public void visit(TTNDeviceInterface ttnSensorInterface) {
		try {
			TTNDevicesController.getInstance().subscribe(ttnSensorInterface);
		}  catch (MqttException e) {
			logger.error(e.getMessage());
		}
		
	}

	@Override
	public void visit(HttpDeviceInterface httpDeviceInterface) {}


}
