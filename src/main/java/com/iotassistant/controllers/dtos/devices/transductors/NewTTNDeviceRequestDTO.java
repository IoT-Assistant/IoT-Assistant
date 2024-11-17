package com.iotassistant.controllers.dtos.devices.transductors;

import javax.validation.constraints.Pattern;

import com.iotassistant.controllers.dtos.devices.NewDeviceRequestDTO;

public class NewTTNDeviceRequestDTO extends NewDeviceRequestDTO{
	
	
	@Pattern(regexp = "^[a-z0-9\\-]{3,50}$")
	private String deviceId;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}
