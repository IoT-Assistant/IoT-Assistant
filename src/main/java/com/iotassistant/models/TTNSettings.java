package com.iotassistant.models;

import javax.persistence.Entity;

@Entity
public class TTNSettings extends MqttSettings{

	public TTNSettings() {
		super();
	}

	public TTNSettings(String broker, String clientId, String username, String password) {
		super(broker, clientId, username, password);
	}

	
	
}
