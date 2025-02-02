package com.iotassistant.controllers.dtos.devices;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewDeviceRequestDTO {
	
	@JsonProperty(required = true)
    @NotNull
	@Pattern(regexp = "^[A-Za-z0-9 ]{0,30}$")
    private String name;
	
	@JsonProperty(required = true)
    @NotNull
    @Size(max = 50)
	private String description;
	
	@JsonProperty(required = true)
    @NotNull
	private String watchdogInterval;
	

	public String getName() {
		return name;
	}


	public void setName(String sensorName) {
		this.name = sensorName;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public void setWatchdogInterval(String watchdogInterval) {
		this.watchdogInterval = watchdogInterval;
	}


	public String getDescription() {
		return description;
	}


	public String getWatchdogInterval() {
		return watchdogInterval;
	}

	
	
	
	

}
