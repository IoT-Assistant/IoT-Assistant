package com.iotassistant.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SystemSettings {
	
	@Id
    @GeneratedValue
    private int id;
	
	@OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade=CascadeType.ALL)
	private SecuritySettings securitySettings;
	
	@OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade=CascadeType.ALL)
	private MqttSettings mqttSettings;
	
	@OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade=CascadeType.ALL)
	private TTNSettings ttnSettings;
	
	@OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade=CascadeType.ALL)
	private TelegramSettings telegramSettings;
	

	public SystemSettings() {
		super();
	}


	public SystemSettings(SecuritySettings securitySettings, MqttSettings mqttSettings, TTNSettings ttnSettings, TelegramSettings telegramSettings) {
		super();
		this.securitySettings = securitySettings;
		this.mqttSettings = mqttSettings;
		this.ttnSettings = ttnSettings;
		this.telegramSettings = telegramSettings;
	}


	public SecuritySettings getSecuritySettings() {
		return securitySettings;
	}


	public MqttSettings getMqttSettings() {
		return mqttSettings;
	}


	public TTNSettings getTtnSettings() {
		return ttnSettings;
	}

	public TelegramSettings getTelegramSettings() {
		return this.telegramSettings;
	}

	public String getSecurityUsername() {
		return this.getSecuritySettings().getUsername();
	}


	public String getSecurityPassword() {
		return this.getSecuritySettings().getPassword();
	}

}
