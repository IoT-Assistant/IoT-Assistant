package com.iotassistant.models.devices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GpsPosition {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String longitude;
	
	private String latitude;
	
	private String date;
	

	public GpsPosition() {
		super();
	}


	public GpsPosition(String longitude, String latitude, String date) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.date = date;
	}


	public String getLongitude() {
		return longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public String getDate() {
		return date;
	}


	public static boolean isValidPosition(String longitude, String latitude) {
		try {
			return (-180 < Float.parseFloat(longitude) &&  Float.parseFloat(longitude) < 180
					&& -90 < Float.parseFloat(latitude) && Float.parseFloat(latitude) < 90);
		} catch (NullPointerException | NumberFormatException e) {
			return false;
		}
	}

	
	
	
	

}
