package com.iotassistant.mqtt;

import java.util.HashMap;
import java.util.Map;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotassistant.models.MqttSettings;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;
import com.iotassistant.services.system.SystemSettingsService;



@Controller
public class MqttDevicesController implements MqttCallbackExtended{
	
	private volatile IMqttClient mqttclient;
	
	private static MqttDevicesController instance;
	
	private String brokerURL;
	
	Logger logger = LoggerFactory.getLogger(MqttDevicesController.class);

	
	@Autowired
	public MqttDevicesController(SystemSettingsService systemSettingsService) {	
		super();
		this.connect(systemSettingsService);
	}
	

	public void connect(SystemSettingsService systemSettingsService) {
		MqttSettings mqttSettings = systemSettingsService.getSettings().getMqttSettings();
		this.brokerURL = mqttSettings.getBroker();
		try {
			if (this.isConnected()) {
				this.mqttclient.disconnect();
			}
			if (brokerURL != null) {
				mqttclient = new MqttClient(brokerURL, mqttSettings.getClientId(), new MqttDefaultFilePersistence("/tmp"));
				mqttclient.setCallback(this);
				mqttclient.connect(this.getMqttConnectOptions(mqttSettings.getUsername(),  mqttSettings.getPassword()));
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
	
	
	public static MqttDevicesController getInstance() {
		return instance;
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

	
	public void subscribe(MqttDeviceInterface mqttinterface) throws MqttSecurityException, MqttException {
		for (String topic : mqttinterface.getSubscribedTopics()) {
			this.mqttclient.subscribe(topic);
		}
	}
	
	public void unsubscribe(MqttDeviceInterface mqttinterface) throws MqttSecurityException, MqttException {
		for (String topic : mqttinterface.getSubscribedTopics()) {
			this.mqttclient.unsubscribe(topic);
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage message)  {
		logger.debug("Message arrived: topic: " + topic + " message: " + message.toString());
		new MqttArrivedMessageService(topic, message).updateDevice();		
	}
	
	public void setActuatorValue(MqttActuatorInterface actuatorMqttInterface, PropertyActuatedEnum propertyActuated, String value) {
		try {
			Map<String, String> newValue;
			newValue = new HashMap<String, String>();
			newValue.put(propertyActuated.getNameWithUnit(), value);
			byte[] jsonBytes = new ObjectMapper().writeValueAsString(newValue).getBytes();
			this.mqttclient.publish(actuatorMqttInterface.getSetValueTopic(), new MqttMessage(jsonBytes));
		} catch (JsonProcessingException | MqttException e) {
			logger.error(e.getMessage());
		} 
		
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
