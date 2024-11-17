package com.iotassistant.ttn;

import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iotassistant.models.TTNSettings;
import com.iotassistant.services.system.SystemSettingsService;

@Controller
public class TTNDevicesController implements MqttCallbackExtended{
	
	private volatile IMqttClient mqttclient;
	
	private static TTNDevicesController instance;
	
	private  String applicationId;
	
	private  String brokerURL;
	
	Logger logger = LoggerFactory.getLogger(TTNDevicesController.class);
	

	@Autowired
	public TTNDevicesController(SystemSettingsService systemSettingsService) {
		super();
		this.connect(systemSettingsService);
	}
	

	public void connect(SystemSettingsService systemSettingsService) {
		TTNSettings ttnSettings = systemSettingsService.getSettings().getTtnSettings();
		this.applicationId = ttnSettings.getUsername();
		this.brokerURL = ttnSettings.getBroker();
		try {
			if (this.isConnected()) {
				this.mqttclient.disconnect();
			}
			if (brokerURL != null) {
				mqttclient = new MqttClient(brokerURL, ttnSettings.getClientId(), new MqttDefaultFilePersistence("/tmp") );
				mqttclient.setCallback(this);
				mqttclient.connect(this.getMqttConnectOptions(ttnSettings.getUsername(), ttnSettings.getPassword()));
			} else {
				mqttclient = new MqttClient("ssl://0.0.0.0:0000", "");
			}
		} catch (MqttException e) { 
			logger.error(e.getMessage());
		} 	
	}


	@PostConstruct
	private void registerInstance() {
		instance = this;
	} 
	
	
	public static TTNDevicesController getInstance() {
		return instance;
	}
	

	public String getApplicationId() {
		return applicationId;
	}


	private MqttConnectOptions getMqttConnectOptions(String username, String password) {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(false);
		options.setConnectionTimeout(0);
		if (username!= null && password != null) {
			options.setUserName(username);
			options.setPassword(password.toCharArray());
		}		
		return options;
	}

	
	public void subscribe(TTNDeviceInterface mqttinterface) throws MqttSecurityException, MqttException {
		for (String topic : mqttinterface.getSubscribedTopics()) {
			this.mqttclient.subscribe(topic);
		}
	}
	
	public void unsubscribe(TTNDeviceInterface mqttinterface) throws MqttSecurityException, MqttException {
		for (String topic : mqttinterface.getSubscribedTopics()) {
			this.mqttclient.unsubscribe(topic);
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage message)  {
		logger.debug("Message arrived: topic: " + topic + " message: " + message.toString());
		new TTNArrivedMessageService(topic, message).updateDevice();		
	}
	

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	}

	@Override
	public void connectComplete(boolean reconnect, String serverURI) {
		logger.info("Connection to MQTT broker completed: " + serverURI);
	}
	
	@Override
	public void connectionLost(Throwable cause) {
		logger.info("Connection to MQTT broker lost: " + cause.getMessage());
	}

	public boolean isConnected() {
		return mqttclient != null && mqttclient.isConnected();
	}
	
	@Override
	public void finalize() {
		try {
			logger.info("Disconnecting from MQTT broker");
			mqttclient.disconnect();
		} catch (MqttException e) {
			logger.error(e.getMessage());
		}
	}


	public String getBroker() {
		return this.brokerURL;
	}

}

