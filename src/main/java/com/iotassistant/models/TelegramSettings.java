package com.iotassistant.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TelegramSettings {
	
	@Id
    @GeneratedValue
    private int id;
	
	private String token;
	
	private String chatId;
	
	private String username;

	public TelegramSettings() {
		super();
	}

	public TelegramSettings(String token, String chatId, String username) {
		super();
		this.token = token;
		this.chatId = chatId;
		this.username = username;
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
	
	

}
