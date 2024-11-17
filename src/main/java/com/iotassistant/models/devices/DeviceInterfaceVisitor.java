package com.iotassistant.models.devices;

import com.iotassistant.models.HttpDeviceInterface;
import com.iotassistant.mqtt.MqttDeviceInterface;
import com.iotassistant.ttn.TTNDeviceInterface;

public interface DeviceInterfaceVisitor {

	void visit(MqttDeviceInterface mqttInterface) ;
	
	void visit(HttpDeviceInterface httpDeviceInterface);

	void visit(TTNDeviceInterface ttnSensorInterface);	
	

}
