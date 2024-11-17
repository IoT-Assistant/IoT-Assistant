package com.iotassistant.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotassistant.models.MqttSettings;

public class MqttSettingsDTO {
	
	private String broker;
	
	private String clientId;
	
	private String username;
	
	private String password;

	public MqttSettingsDTO() {
		super();
	}

	public MqttSettingsDTO(MqttSettings mqttSettings) {
		this.broker = mqttSettings.getBroker();
		this.clientId = mqttSettings.getClientId();
		this.username = mqttSettings.getUsername();
		this.password = mqttSettings.getPassword();
	}

	public String getBroker() {
		return broker;
	}

	public String getClientId() {
		return clientId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	@JsonIgnore
	public MqttSettings getMqttSettings() {
		return new MqttSettings(broker, clientId, username, password);
	}

}
