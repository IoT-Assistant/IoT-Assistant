package com.iotassistant.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SecuritySettings {
	
	@Id
    @GeneratedValue
    private int id;
		
	private String username;	

	private String password;
	

	public SecuritySettings() {
		super();
	}

	public SecuritySettings(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
