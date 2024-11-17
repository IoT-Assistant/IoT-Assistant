package com.iotassistant.services.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.iotassistant.models.MqttSettings;
import com.iotassistant.models.SecuritySettings;
import com.iotassistant.models.SystemSettings;
import com.iotassistant.models.TTNSettings;
import com.iotassistant.models.TelegramSettings;
import com.iotassistant.repositories.SystemSettingsJPARepository;

@Service
public class SystemSettingsService {
	
	public static final String DEFAULT_USERNAME = "admin";
	
	public static final String DEFAULT_PASSWORD = "iotassistant";
	
	SystemSettingsJPARepository systemSettingsJPARepository;
	
	private UserDetailsService userDetailsService;
	
	@Autowired
	public SystemSettingsService(SystemSettingsJPARepository systemSettingsJPARepository) {
		super();
		this.systemSettingsJPARepository = systemSettingsJPARepository;
		if (getSettings() == null) {
			systemSettingsJPARepository.saveAndFlush(getDefaultSettings());
		}
		this.userDetailsService = new InMemoryUserDetailsManager();
		((InMemoryUserDetailsManager) this.userDetailsService).createUser(User.withUsername(getSettings().getSecurityUsername())
        		.password(passwordEncoder().encode(getSettings().getSecurityPassword())).roles("ADMIN").build());
	}

	public SystemSettings getSettings() {
		return systemSettingsJPARepository.findFirstByOrderByIdDesc();
	}

	public void setSettings(SystemSettings newSettings) {
		SystemSettings currentSettings = this.getSettings();
		((InMemoryUserDetailsManager)userDetailsService()).changePassword("", passwordEncoder().encode(newSettings.getSecurityPassword()));
		systemSettingsJPARepository.delete(currentSettings);
		systemSettingsJPARepository.saveAndFlush(newSettings);
	}
	
    public UserDetailsService userDetailsService()  {
        return userDetailsService;
    }

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	private SystemSettings getDefaultSettings() {
		SecuritySettings security = new SecuritySettings(DEFAULT_USERNAME, DEFAULT_PASSWORD);
		MqttSettings mqtt = new MqttSettings();
		TTNSettings ttn = new TTNSettings();
		TelegramSettings telegramSettings = new TelegramSettings();
		SystemSettings settings = new SystemSettings(security, mqtt, ttn, telegramSettings);
		return settings;
	}

}
