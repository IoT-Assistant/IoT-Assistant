package com.iotassistant.mqtt;

import java.util.ArrayList;
import java.util.List;

public interface MqttDeviceInterface extends MqttInterface{
	
	@Override
	public default List<String> getSubscribedTopics() {
		List<String> subscribedTopics = new ArrayList<String>();
		subscribedTopics.add(this.getDeviceTopic());
		return subscribedTopics;
	}
	
	public MqttDeviceType getDeviceType();

	public default String getDeviceTopic() {
		return this.getDeviceType().getBaseTopic() + this.getDeviceSubTopic();
	}
	
	public String getDeviceSubTopic();

	public static String getDeviceName(String topic) {
		return topic.split("/", 2)[1];
	}

}
