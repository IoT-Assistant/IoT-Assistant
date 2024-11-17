package com.iotassistant.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotassistant.models.SecuritySettings;

public class SecuritySettingsDTO {
	
	private String username;
	
	private String password;
	
	public SecuritySettingsDTO() {
		super();
	}

	public SecuritySettingsDTO(SecuritySettings securitySettings) {
		this.username = securitySettings.getUsername();
		this.password = securitySettings.getPassword();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	@JsonIgnore
	public SecuritySettings getSecuritySettings() {
		return new SecuritySettings(username, password);
	}


	
	

}
