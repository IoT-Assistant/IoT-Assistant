package com.iotassistant.services.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.ServerStatus;
import com.iotassistant.models.TelegramBotManager;
import com.iotassistant.models.devices.transductors.TransductorInterfaceTypeEnum;
import com.iotassistant.models.notifications.NotificationTypeEnum;
import com.iotassistant.mqtt.MqttDevicesController;
import com.iotassistant.ttn.TTNDevicesController;

@Service
public class ServersStatusService {
	
	private @Autowired
	TelegramBotManager telegramBotManager;
	
	@Autowired
	TTNDevicesController ttnDevicesController;
	
	@Autowired
	MqttDevicesController mqttDevicesController;
	

	public List<ServerStatus> getServersStatus() {
		List<ServerStatus> serversStatus = new ArrayList<ServerStatus>();
		serversStatus.add(new ServerStatus( TransductorInterfaceTypeEnum.MQTT.toString(), mqttDevicesController.isConnected(), "Broker: " + (mqttDevicesController.getBroker() == null? "Not set" : mqttDevicesController.getBroker())));
		serversStatus.add(new ServerStatus(NotificationTypeEnum.TELEGRAM.toString(), telegramBotManager.connected(), "Bot: " + telegramBotManager.getBotUsername()));
		serversStatus.add(new ServerStatus(TransductorInterfaceTypeEnum.TTN.toString(), ttnDevicesController.isConnected(), "Broker: " + (ttnDevicesController.getBroker() == null? "Not set" : ttnDevicesController.getBroker())));
		return serversStatus;
		
	}	

}
