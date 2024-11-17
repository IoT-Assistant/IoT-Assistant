package com.iotassistant.mqtt;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.iotassistant.models.devices.transductors.SensorValues;
import com.iotassistant.models.devices.transductors.TransductorValues;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.utils.Date;

//Sensor Values JSON Example : {"TEMPERATURE_CENTIGRADES" : "25"} or  {"Motion" : "true"}
class MqttSensorValuesDTO extends MqttTransductorValuesDTO<PropertyMeasuredEnum>{
	
		
	@JsonCreator
	public MqttSensorValuesDTO(HashMap<String, String> values){
		super(values);
	}
	
	@Override
	protected TransductorValues<PropertyMeasuredEnum> createTransductorValues() {
		return new SensorValues(new HashMap<PropertyMeasuredEnum, String>(), Date.getCurrentDate());
	}

	@Override
	protected PropertyMeasuredEnum getPropertyInstance(String propertyString) {
		return PropertyMeasuredEnum.getInstance(propertyString);
	}
	
	public SensorValues getSensorValues() {
		return (SensorValues) this.getTransductorValues();
	}

}
