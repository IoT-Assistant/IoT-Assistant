package com.iotassistant.controllers.dtos.devices;

import com.iotassistant.controllers.dtos.devices.transductors.NewTTNDeviceRequestDTO;
import com.iotassistant.models.devices.Gps;
import com.iotassistant.models.devices.WatchdogInterval;
import com.iotassistant.ttn.TTNGpsInterface;

public class NewTTNInterfaceGpsDTO extends NewTTNDeviceRequestDTO{

	public NewTTNInterfaceGpsDTO() {
		super();
	}

	public Gps getGps() {
		TTNGpsInterface gpsTTNInterface = new TTNGpsInterface(this.getDeviceId());
		return new Gps(super.getName(), super.getDescription(), gpsTTNInterface, WatchdogInterval.getInstance(super.getWatchdogInterval()));	

	}

}
