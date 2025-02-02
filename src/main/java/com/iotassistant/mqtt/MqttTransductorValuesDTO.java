package com.iotassistant.mqtt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.iotassistant.models.devices.transductors.Property;
import com.iotassistant.models.devices.transductors.TransductorValues;

abstract class MqttTransductorValuesDTO<T extends Property> {
	
	private TransductorValues<T> transductorValues;
	
	private List<String> errors;
	
	@JsonCreator MqttTransductorValuesDTO(HashMap<String, String> values){
		super();
		this.errors = new ArrayList<String>();
		deserialize(values);
	}
	
	private void deserialize(HashMap<String, String> values)  {
		this.transductorValues = this.createTransductorValues();
		for(String propertyString : values.keySet() ) {
			T property = this.getPropertyInstance(propertyString);
			if (property == null) {
				this.getErrors().add("Unknown property measured " + propertyString);
			} else {
				String value = values.get(propertyString);
				this.transductorValues.getValues().put(property, value);
				if (!property.isValidValue(value)) {
					this.getErrors().add("Value is invalid :" + value);	
				}
			}
		}		
	}

	protected abstract T getPropertyInstance(String propertyString);

	protected abstract TransductorValues<T> createTransductorValues();

	List<String> getErrors() {
		return errors;
	}
	
	boolean hasErrors(List<T> properties) {
		Set<T> dtoProperties = this.transductorValues.getValues().keySet();
		for (T property: properties) {
			if (!dtoProperties.contains(property)) {
				this.getErrors().add(property.getNameWithUnit() + " not found in DTO");
			}
		}
		return !this.getErrors().isEmpty();
	}

	protected TransductorValues<T> getTransductorValues() {
		return transductorValues;
	}
	
	
	
}
