package com.iotassistant.models;

import java.io.ByteArrayInputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.iotassistant.services.system.SystemSettingsService;

@Component
public class TelegramBotManager extends TelegramLongPollingBot{
	
	private String telegramToken;

	private String telegramUsername;	

	private String botChatId;
	
	private static TelegramBotManager instance;
	
	@Autowired
	public TelegramBotManager(SystemSettingsService systemSettingsService) {
		super();
		this.connect(systemSettingsService);
	}

	public void connect(SystemSettingsService systemSettingsService) {
		TelegramSettings telegramSettings = systemSettingsService.getSettings().getTelegramSettings();
		this.telegramToken = telegramSettings.getToken();
		this.telegramUsername = telegramSettings.getUsername();
		this.botChatId = telegramSettings.getChatId();
	}
	
	@PostConstruct
	private void registerInstance() {
		instance = this;
	} 
	
	
	public static TelegramBotManager getInstance() {
		return instance;
	}

	public TelegramBotManager() {
		super();
	}

	@Override
	public void onUpdateReceived(Update update) {
	}

	@Override
	public String getBotUsername() {
		return telegramUsername;
	}

	@Override
	public String getBotToken() {
		return telegramToken;
	}

	public void sendTelegramTextMessage(String telegramMessage) throws TelegramApiException {
		SendMessage msg = new SendMessage();
		msg.setParseMode(ParseMode.MARKDOWN);
		msg.setText(telegramMessage);
		msg.setChatId(botChatId);
		execute(msg);	
	}
	
	public void sendTelegramPictureMessage(String telegramMessage, byte[] picture) throws TelegramApiException {
		SendPhoto msg = new SendPhoto();
		msg.setParseMode(ParseMode.MARKDOWN);
		msg.setChatId(botChatId);
		msg.setNewPhoto(telegramMessage, new ByteArrayInputStream(picture));
		sendPhoto(msg);
		
	}
	
	
	
	public boolean connected() {
		try {
			this.getMe();
			return true;
		} catch (TelegramApiException e) {
			return false;
		}
	}

}
