package com.iotassistant.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotassistant.models.SystemSettings;

public class SystemSettingsDTO {
	
	private SecuritySettingsDTO security;
	
	private MqttSettingsDTO mqtt;
	
	private TTNSettingsDTO ttn;
	
	private TelegramSettingsDTO telegram;

	public SystemSettingsDTO() {
		super();
	}

	public SystemSettingsDTO(SystemSettings settings) {
		this.security = new SecuritySettingsDTO(settings.getSecuritySettings());
		this.mqtt = new MqttSettingsDTO(settings.getMqttSettings());
		this.ttn = new TTNSettingsDTO(settings.getTtnSettings());
		this.telegram = new TelegramSettingsDTO(settings.getTelegramSettings());
	}

	public SecuritySettingsDTO getSecurity() {
		return security;
	}

	public MqttSettingsDTO getMqtt() {
		return mqtt;
	}

	public TTNSettingsDTO getTtn() {
		return ttn;
	}

	public TelegramSettingsDTO getTelegram() {
		return telegram;
	}

	@JsonIgnore
	public SystemSettings getSystemSettings() {
		return new SystemSettings(this.security.getSecuritySettings(), this.mqtt.getMqttSettings(), this.ttn.getTTNSettings(), this.telegram.getTelegramSettings());
	}

}
