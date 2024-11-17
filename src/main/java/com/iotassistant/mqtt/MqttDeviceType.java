package com.iotassistant.mqtt;

import com.iotassistant.services.ActuatorsService;
import com.iotassistant.services.DeviceService;
import com.iotassistant.services.GpsesService;
import com.iotassistant.services.SensorsService;

public enum MqttDeviceType {
	SENSORS("sensor/", SensorsService.getInstance()),
	ACTUATORS("actuator/", ActuatorsService.getInstance()),
	GPS("gps/", GpsesService.getInstance());
	
	private String baseTopic;
	
	private DeviceService devicesService;

	MqttDeviceType(String baseTopic, DeviceService devicesService) {
		this.baseTopic = baseTopic;
		this.devicesService = devicesService;
	}

	public String getBaseTopic() {
		return baseTopic;
	}

	public DeviceService getDevicesService() {
		return devicesService;
	}
	
	public static MqttDeviceType getInstance(String topic) {
		for (MqttDeviceType deviceTopic : MqttDeviceType.values()) {
			if (topic.startsWith(deviceTopic.baseTopic)) {
				return deviceTopic;
			}
		}
		return null;
	}
	

}
