package com.iotassistant.ttn;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.iotassistant.models.devices.transductors.Property;
import com.iotassistant.models.devices.transductors.SensorValues;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.utils.Date;

public class TTNSensorValuesDTO {
	
	private static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;
	
	private static final int BINARY_SIZE_BYTES = 1;
	
	private static final int FLOAT_SIZE_BYTES = 4;
	
	private List<String> errors;
	
	private SensorValues sensorValues;
	
	
	@JsonCreator TTNSensorValuesDTO(TTNUplinkDTO ttnUplinkDTO){
		super();
		this.errors = new ArrayList<String>();
		this.sensorValues = new SensorValues(new HashMap<PropertyMeasuredEnum, String>(), Date.getCurrentDate());
		deserialize(ttnUplinkDTO.getPayload());
	}
	
	private void deserialize(String payload) {
		int index = 0;
		byte[] bytes = Base64.getDecoder().decode(payload);
		while (index < bytes.length && this.errors.isEmpty()) {
			TTNPropertyMeasuredId propertyMeasuredId = TTNPropertyMeasuredId.getInstance(bytes[index]);
			if (propertyMeasuredId == null) {
				this.errors.add("Unknown Property id: " + bytes[index]);
			}
			else {
				PropertyMeasuredEnum propertyMeasured = propertyMeasuredId.getPropertyMeasured();
				String value;
				if (propertyMeasured.isBinary()) {
					value = getBinaryValue(bytes, index + TTNPropertyMeasuredId.PROPERTY_ID_SIZE_BYTES);
				} else {
					value = getAnalogValue(propertyMeasured, bytes, index + TTNPropertyMeasuredId.PROPERTY_ID_SIZE_BYTES);	
				}
				if (this.errors.isEmpty()) {
					this.sensorValues.getValues().put(propertyMeasured, value);
					index += TTNPropertyMeasuredId.PROPERTY_ID_SIZE_BYTES + (propertyMeasured.isBinary() ? BINARY_SIZE_BYTES : FLOAT_SIZE_BYTES);
				}
				
			} 	
		}
	}
	

	private String getAnalogValue(PropertyMeasuredEnum propertyMeasured, byte[] bytes, int index) {
		if (bytes.length < index + FLOAT_SIZE_BYTES || !isValidAnalogValue(propertyMeasured, bytes, index)) {
			this.errors.add("Invalid analog value: " + bytes[index]);
			return null;
		} 
		byte[] floatBytes = Arrays.copyOfRange(bytes, index, index + FLOAT_SIZE_BYTES);
		float floatValue = ByteBuffer.wrap(floatBytes).order(BYTE_ORDER).getFloat();
		return String.valueOf(floatValue);
		
		
	}

	private String getBinaryValue(byte[] bytes, int index) {
		if (bytes.length < index +  BINARY_SIZE_BYTES || !isValidBinaryValue(bytes[index])) {
			this.errors.add("Invalid binary value: " + bytes[index]);
			return null;
		} 
		return Property.getStringFromBoolean(bytes[index] == 1);	
	}

	private boolean isValidAnalogValue(PropertyMeasuredEnum propertyMeasured, byte[] bytes, int index) {
		try {
			byte[] floatBytes = Arrays.copyOfRange(bytes, index, index + FLOAT_SIZE_BYTES);
			float floatValue = ByteBuffer.wrap(floatBytes).order(BYTE_ORDER).getFloat();
			return propertyMeasured.isValidValue(String.valueOf(floatValue));
		} catch (Exception e) {
			this.errors.add("Invalid analog value");	
			return false;
		}
	}

	private boolean isValidBinaryValue(byte value) {
		return value == 1 || value == 0;
	}

	public boolean hasErrors(List<PropertyMeasuredEnum> sensorProperties) {
		Set<PropertyMeasuredEnum> propertiesInDTO = this.sensorValues.getValues().keySet();
		for (PropertyMeasuredEnum property: sensorProperties) {
			if (!propertiesInDTO.contains(property)) {
				this.getErrors().add(property.getNameWithUnit() + " not found in DTO");
			}
		}
		return !this.getErrors().isEmpty();
	}

	public List<String> getErrors() {
		return this.errors;
	}

	public SensorValues getSensorValues() {
		return this.sensorValues;
	}

}
