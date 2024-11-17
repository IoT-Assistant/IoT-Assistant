package com.iotassistant.ttn;

import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;

public enum TTNPropertyMeasuredId {
	BINARY_MOTION(PropertyMeasuredEnum.BINARY_MOTION, (byte) 1),
	TEMPERATURE_CENTIGRADES(PropertyMeasuredEnum.TEMPERATURE_CENTIGRADES, (byte) 2),
	HUMIDITY_PERCENTAGE(PropertyMeasuredEnum.HUMIDITY_PERCENTAGE, (byte) 3),
	AMBIENT_LIGHT_LUX(PropertyMeasuredEnum.AMBIENT_LIGHT_LUX, (byte) 4),
	BINARY_TILT(PropertyMeasuredEnum.BINARY_TILT, (byte) 5),
	AIR_PRESSURE_PA(PropertyMeasuredEnum.AIR_PRESSURE_PA, (byte) 6),
	AIR_QUALITY_IAQ(PropertyMeasuredEnum.AIR_QUALITY_IAQ, (byte) 7),
	UV_UVI(PropertyMeasuredEnum.UV_UVI, (byte) 8),
	UV_UVA(PropertyMeasuredEnum.UV_UVA, (byte) 9),
	UV_UVB(PropertyMeasuredEnum.UV_UVB, (byte) 10),
	BINARY_FLAME(PropertyMeasuredEnum.BINARY_FLAME, (byte) 11),
	BINARY_STEAM(PropertyMeasuredEnum.BINARY_STEAM, (byte) 12),
	BINARY_SOUND(PropertyMeasuredEnum.BINARY_SOUND, (byte) 13),
	BINARY_LIMIT_SWITCH(PropertyMeasuredEnum.BINARY_LIMIT_SWITCH, (byte) 14),
	AIR_CO_PPM(PropertyMeasuredEnum.AIR_CO_PPM, (byte) 15),
	AIR_CO2_PPM(PropertyMeasuredEnum.AIR_CO2_PPM, (byte) 16),
	AIR_TVOC_PPB(PropertyMeasuredEnum.AIR_TVOC_PPB, (byte) 17),
	SOIL_MOISTURE_RH(PropertyMeasuredEnum.SOIL_MOISTURE_RH, (byte) 18),
	BINARY_DUAL_BUTTON_A(PropertyMeasuredEnum.BINARY_DUAL_BUTTON_A, (byte) 19),
	BINARY_DUAL_BUTTON_B(PropertyMeasuredEnum.BINARY_DUAL_BUTTON_B, (byte) 20),
	ENCODER_60U(PropertyMeasuredEnum.ENCODER_60U, (byte) 21),
	HEART_RATE_PPM(PropertyMeasuredEnum.HEART_RATE_PPM, (byte) 22),
	PULSE_OXYMETER_PERCENTAGE(PropertyMeasuredEnum.PULSE_OXYMETER_PERCENTAGE, (byte) 23),
	GESTURE_ID(PropertyMeasuredEnum.GESTURE_ID, (byte) 24),
	VOLTAGE_V(PropertyMeasuredEnum.VOLTAGE_V, (byte) 25),
	CURRENT_A(PropertyMeasuredEnum.CURRENT_A, (byte) 26),
	POWER_W(PropertyMeasuredEnum.POWER_W, (byte) 27),
	BATTERY_PERCENTAGE(PropertyMeasuredEnum.BATTERY_PERCENTAGE, (byte) 28),
	VOICE_ASSISTANT_CMDID(PropertyMeasuredEnum.VOICE_ASSISTANT_CMDID, (byte) 29),
	GENERIC_NA(PropertyMeasuredEnum.GENERIC_NA, (byte) 30),
	BINARY_BUTTON(PropertyMeasuredEnum.BINARY_BUTTON, (byte) 31),
	BINARY_GENERIC(PropertyMeasuredEnum.BINARY_GENERIC, (byte) 32),
	TEMPERATURE_FARENHEIT(PropertyMeasuredEnum.TEMPERATURE_FARENHEIT, (byte) 33),
	PH_PH(PropertyMeasuredEnum.PH, (byte) 34),
	WEIGHT_GRAM(PropertyMeasuredEnum.WEIGHT_GRAM, (byte) 35);
	
	private PropertyMeasuredEnum propertyMeasured;
	
	private byte id;
	
	public static final int PROPERTY_ID_SIZE_BYTES = 1;

	TTNPropertyMeasuredId(PropertyMeasuredEnum propertyMeasured, byte id) {
		this.propertyMeasured = propertyMeasured;
		this.id = id;
	}

	public PropertyMeasuredEnum getPropertyMeasured() {
		return propertyMeasured;
	}
	
	

	public byte getId() {
		return id;
	}

	public static TTNPropertyMeasuredId getInstance(byte id) {
		for (TTNPropertyMeasuredId propertyMeasuredId : TTNPropertyMeasuredId.values()) { 
			if (propertyMeasuredId.getId() == id) {
				return propertyMeasuredId;
			}; 		
		}
		return null;
	}

}
