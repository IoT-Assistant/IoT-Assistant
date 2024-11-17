package com.iotassistant.mqtt;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iotassistant.models.devices.DeviceInterfaceVisitor;
import com.iotassistant.models.devices.transductors.ActuatorInterface;
import com.iotassistant.models.devices.transductors.ActuatorInterfaceVisitor;

@Entity
@DiscriminatorValue("actuatorMQTTInterface")
public class MqttActuatorInterface extends ActuatorInterface implements MqttDeviceInterface{
	
	private String topic;
	
	private static String SET_VALUE_TOPIC = "/value";

	public MqttActuatorInterface() {
		super();		
	}

	public MqttActuatorInterface(String topic) {
		this();
		this.topic = topic;	
	}

	@Override
	public void accept(DeviceInterfaceVisitor deviceInterfaceVisitor) {
		deviceInterfaceVisitor.visit(this);
		
	}

	@Override
	public String getDeviceSubTopic() {
		return this.topic;
	}

	@Override
	public void accept(ActuatorInterfaceVisitor actuatorInterfaceVisitor) {
		actuatorInterfaceVisitor.visit(this);		
	}
	
	public String getSetValueTopic() {
		return this.getDeviceTopic().concat(SET_VALUE_TOPIC);
	}


	@Override
	public MqttDeviceType getDeviceType() {
		return MqttDeviceType.ACTUATORS;
	}
	

}
