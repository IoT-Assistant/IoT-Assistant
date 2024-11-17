package com.iotassistant.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotassistant.models.TTNSettings;

public class TTNSettingsDTO {
	
	private String broker;
	
	private String clientId;
	
	private String username;
	
	private String password;

	public TTNSettingsDTO() {
		super();
	}

	public TTNSettingsDTO(TTNSettings ttnSettings) {
		this.broker = ttnSettings.getBroker();
		this.clientId = ttnSettings.getClientId();
		this.username = ttnSettings.getUsername();
		this.password = ttnSettings.getPassword();
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
	public TTNSettings getTTNSettings() {
		return new TTNSettings(broker, clientId, username, password);
	}

}
