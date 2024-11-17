package com.iotassistant.controllers.dtos;

public class SystemDTO  {
	
	private SystemCapabilitiesDTO capabilities;
	
	private String uptime;
	
	public SystemDTO(SystemCapabilitiesDTO capabilities, String uptime) {
		super();
		this.capabilities = capabilities;
		this.uptime = uptime;
	}

	public SystemCapabilitiesDTO getCapabilities() {
		return capabilities;
	}

	public String getUptime() {
		return uptime;
	}
	

}
