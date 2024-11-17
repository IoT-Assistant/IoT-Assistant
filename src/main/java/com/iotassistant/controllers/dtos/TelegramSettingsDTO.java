package com.iotassistant.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotassistant.models.TelegramSettings;

public class TelegramSettingsDTO {
	
	private String token;
	
	private String chatId;
	
	private String username;

	public TelegramSettingsDTO() {
		super();
	}

	public TelegramSettingsDTO(TelegramSettings telegramSettings) {
		this.token = telegramSettings.getToken();
		this.chatId = telegramSettings.getChatId();
		this.username = telegramSettings.getUsername();
	}

	public String getToken() {
		return token;
	}

	public String getChatId() {
		return chatId;
	}

	public String getUsername() {
		return username;
	}

	@JsonIgnore
	public TelegramSettings getTelegramSettings() {
		return new TelegramSettings(this.token, this.chatId, this.username);
	}
	
	

}
