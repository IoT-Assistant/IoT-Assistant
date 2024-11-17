package com.iotassistant.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MqttSettings {
	
	@Id
    @GeneratedValue
    private int id;
	
	private String broker;
	
	private String clientId;
	
	private String username;
	
	private String password;	

	public MqttSettings() {
		super();
	}

	public MqttSettings(String broker, String clientId, String username, String password) {
		super();
		this.broker = broker;
		this.clientId = clientId;
		this.username = username;
		this.password = password;
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

}
