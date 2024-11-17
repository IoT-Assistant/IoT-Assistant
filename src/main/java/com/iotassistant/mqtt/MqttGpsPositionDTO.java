package com.iotassistant.mqtt;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.utils.Date;

//GPS JSON Example : { "longitude" : "40.753", "latitude": "-73.983"} 
public class MqttGpsPositionDTO {
	
	private List<String> errors;
	
	private String longitude;
	
	private String latitude;

	
	@JsonCreator MqttGpsPositionDTO(@JsonProperty(value = "longitude" , required = true) String longitude,
									@JsonProperty(value = "latitude" , required = true) String latitude){
		super();
		this.errors = new ArrayList<String>();
		this.deserialize(longitude, latitude);
	}
	
	private void deserialize(String longitude, String latitude)  {

		if (!GpsPosition.isValidPosition(longitude, latitude)) {
			this.getErrors().add("position is invalid, longitude: " + longitude + " latitude: " + latitude);	
		}
		this.longitude = longitude;
		this.latitude = latitude;
			
	}


	List<String> getErrors() {
		return errors;
	}
	
	boolean hasErrors() {
		return !this.getErrors().isEmpty();
	}

	protected GpsPosition getPosition() {
		return new GpsPosition(this.longitude, this.latitude, Date.getCurrentDate());
	}

}
