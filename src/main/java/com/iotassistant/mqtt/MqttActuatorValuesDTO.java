package com.iotassistant.mqtt;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.iotassistant.models.devices.transductors.ActuatorValues;
import com.iotassistant.models.devices.transductors.TransductorValues;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;
import com.iotassistant.utils.Date;

//Actuator JSON Example :  {"Led" : "true"} 
class MqttActuatorValuesDTO extends MqttTransductorValuesDTO<PropertyActuatedEnum>{
		
	@JsonCreator
	public MqttActuatorValuesDTO(HashMap<String, String> values){
		super(values);
	}
	
	@Override
	protected TransductorValues<PropertyActuatedEnum> createTransductorValues() {
		return new ActuatorValues(new HashMap<PropertyActuatedEnum, String>(), Date.getCurrentDate());
	}

	@Override
	protected PropertyActuatedEnum getPropertyInstance(String propertyString) {
		return PropertyActuatedEnum.getInstance(propertyString);
	}
	
	public ActuatorValues getSensorValues() {
		return (ActuatorValues) this.getTransductorValues();
	}

}