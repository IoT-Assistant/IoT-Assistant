package com.iotassistant.models.devices.transductors.propertymeasured.analog;

import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredSeverity;

public class AnalogWeightGram extends AnalogPropertyMeasured{
	
	private static final String NAME = "Weight";
	
	private static final String UNIT = "G";
	
	private static final Integer MAXIMUM_VALUE = 1000000;
	
	private static final Integer MINIMUM_VALUE = 0;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getUnit() {
		return UNIT;
	}

	@Override
	public String getDescriptiveInformationFromValue(String value) {
		return null;
	}

	@Override
	public PropertyMeasuredSeverity getSeverity(String value) {
		return null;
	}

	@Override
	public Integer getMaximumValue() {
		return MAXIMUM_VALUE;
	}

	@Override
	public Integer getMinimumValue() {
		return MINIMUM_VALUE;
	}
}

