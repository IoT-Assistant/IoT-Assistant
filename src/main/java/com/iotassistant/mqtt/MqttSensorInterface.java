package com.iotassistant.mqtt;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iotassistant.models.devices.DeviceInterfaceVisitor;
import com.iotassistant.models.devices.transductors.SensorInterface;

@Entity
@DiscriminatorValue("sensorMQTTInterface")
public class MqttSensorInterface extends SensorInterface implements MqttDeviceInterface{
	
	private String topic;
	
	public MqttSensorInterface() {
		super();
	}

	public MqttSensorInterface(String topic) {
		this();
		this.topic = topic;	
	}

	@Override
	public void accept(DeviceInterfaceVisitor deviceInterfaceVisitor){
		deviceInterfaceVisitor.visit(this);	
	}

	@Override
	public String getDeviceSubTopic() {
		return this.topic;
	}


	@Override
	public MqttDeviceType getDeviceType() {
		return MqttDeviceType.SENSORS;
	}


}
