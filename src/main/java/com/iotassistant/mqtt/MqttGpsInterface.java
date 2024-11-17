package com.iotassistant.mqtt;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iotassistant.models.devices.DeviceInterfaceVisitor;
import com.iotassistant.models.devices.GpsInterface;

@Entity
@DiscriminatorValue("gpsMQTTInterface")
public class MqttGpsInterface extends GpsInterface implements MqttDeviceInterface{

	private String topic;
	
	public MqttGpsInterface() {
		super();
	}

	public MqttGpsInterface(String topic) {
		this();
		this.topic = topic;	
	}


	@Override
	public void accept(DeviceInterfaceVisitor transductorInterfaceVisitor){
		transductorInterfaceVisitor.visit(this);	
	}

	@Override
	public String getDeviceSubTopic() {
		return this.topic;
	}


	@Override
	public MqttDeviceType getDeviceType() {
		return MqttDeviceType.GPS;
	}

}
