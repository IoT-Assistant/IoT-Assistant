package com.iotassistant.controllers.dtos.devices.transductors;

import java.util.HashMap;
import java.util.Map;

import com.iotassistant.models.devices.transductors.ActuatorValues;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;
import com.iotassistant.utils.Date;

class ActuatorValuesDTO {
	
	private Map<String, ActuatorValueDTO> values;
	
	private String time;

	ActuatorValuesDTO(ActuatorValues actuatorValues) {
		this.time = Date.getPrettyTime(actuatorValues.getDate());
		this.values = new HashMap<String, ActuatorValueDTO>();
		for (PropertyActuatedEnum propertyActuated: actuatorValues.getValues().keySet()) {
			ActuatorValueDTO sensorValueDTO = new ActuatorValueDTO(propertyActuated, actuatorValues.getValues().get(propertyActuated));
			this.values.put(propertyActuated.getNameWithUnit(), sensorValueDTO);
		}
	}

	public Map<String, ActuatorValueDTO> getValues() {
		return values;
	}

	public String getTime() {
		return time;
	}
	
	

}
