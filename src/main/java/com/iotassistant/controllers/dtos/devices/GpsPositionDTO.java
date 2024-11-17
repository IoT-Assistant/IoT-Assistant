package com.iotassistant.controllers.dtos.devices;

import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.utils.Date;

public class GpsPositionDTO {
	
	private String longitude;
	
	private String latitude;

	private String time;

	public GpsPositionDTO(GpsPosition position) {
		this.longitude = position.getLongitude();
		this.latitude = position.getLatitude();
		this.time = Date.getPrettyTime(position.getDate());
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getTime() {
		return time;
	}
}
