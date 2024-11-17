package com.iotassistant.services;


import org.springframework.beans.factory.annotation.Autowired;

import com.iotassistant.models.devices.Device;
import com.iotassistant.repositories.DevicesJPARepository;

public abstract class DeviceService {
	
	@Autowired
	private DevicesJPARepository devicesJPARepository;
	
	public abstract boolean exist(String name) ;
	
	public abstract Device getByName(String name);
	
	public void enableDisableWatchdog(boolean enable, String deviceName) {
		Device device = this.getByName(deviceName);
		device.setWatchdogEnabled(enable);
		devicesJPARepository.save(device);	
	}
	
	protected void setUpInterface(Device device) {
		new DeviceSetUpInterfaceService().setUp(device.getInterface());	
	}
	
	protected void setDownInterface(Device device) {
		new DeviceSetDownInterfaceService().setDown(device.getInterface());;
		
	}

}
