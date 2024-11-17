package com.iotassistant.ttn;

import java.util.ArrayList;
import java.util.List;

import com.iotassistant.mqtt.MqttInterface;

public interface TTNDeviceInterface extends MqttInterface {

	public String getDeviceId();
	
	@Override
	public default List<String> getSubscribedTopics() {
		List<String> subscribedTopics = new ArrayList<String>();
		String uplinkTopic = "v3/"+TTNDevicesController.getInstance().getApplicationId()+"/devices/"+this.getDeviceId()+"/up";
		subscribedTopics.add(uplinkTopic);
		return subscribedTopics;
	}
	
	public static String getDeviceId(String topic) {
		return topic.split("/")[3];
	}

}
