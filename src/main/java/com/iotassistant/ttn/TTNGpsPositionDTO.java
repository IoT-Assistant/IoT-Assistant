package com.iotassistant.ttn;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.utils.Date;

public class TTNGpsPositionDTO {
	
private List<String> errors;

	private static final int LATITUDE_SIZE_BYTES = 4;
	
	private static final int LONGITUDE_SIZE_BYTES = 4;
	
	private static final int POSITION_SIZE_BYTES = LATITUDE_SIZE_BYTES + LONGITUDE_SIZE_BYTES;
	
	private String longitude;
	
	private String latitude;
	
	@JsonCreator
	public TTNGpsPositionDTO(TTNUplinkDTO ttnUplinkDTO) {
		this.errors = new ArrayList<String>();
		this.deserialize(ttnUplinkDTO.getPayload());
	}
	
	private void deserialize(String payload)  {
		byte[] bytes = Base64.getDecoder().decode(payload);
		if (bytes.length < POSITION_SIZE_BYTES) {
			this.getErrors().add("Payload size is invalid");
			return;
		}
		float latitude = getFloat(Arrays.copyOfRange(bytes, 0, LATITUDE_SIZE_BYTES));
		float longitude = getFloat(Arrays.copyOfRange(bytes, LATITUDE_SIZE_BYTES, POSITION_SIZE_BYTES));
		this.latitude = String.valueOf(latitude);
		this.longitude = String.valueOf(longitude);
		if (!GpsPosition.isValidPosition(this.longitude, this.latitude)) {
			this.getErrors().add("position is invalid, longitude: " + latitude + " latitude: " + longitude);	
		}
		
	}

	private float getFloat(byte[] bytes) {
		return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
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
