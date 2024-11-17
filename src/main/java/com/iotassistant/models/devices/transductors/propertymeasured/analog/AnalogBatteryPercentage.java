package com.iotassistant.models.devices.transductors.propertymeasured.analog;

import java.util.HashMap;

import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredSeverity;

public class AnalogBatteryPercentage extends AnalogPropertyMeasured{
	
	private static final String NAME = "Battery";
	
	private static final String UNIT = "%";
	
	private static final Integer MAXIMUM_VALUE = 120;
	
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
		HashMap<PropertyMeasuredSeverity, String> descriptionsMap = new HashMap<>();
		descriptionsMap.put(PropertyMeasuredSeverity.SEVERITY_GOOD, "Charged");
		descriptionsMap.put(PropertyMeasuredSeverity.SEVERITY_AVERAGE, "More than half");
		descriptionsMap.put(PropertyMeasuredSeverity.SEVERITY_LITTLE_BAD, "Less than half");
		descriptionsMap.put(PropertyMeasuredSeverity.SEVERITY_BAD,"Less than a third");
		descriptionsMap.put(PropertyMeasuredSeverity.SEVERITY_VERY_BAD, "Almost empty");
		return descriptionsMap.get(getSeverity(value));
	}

	@Override
	public PropertyMeasuredSeverity getSeverity(String value) {
		float battery = Float.parseFloat(value);
		if(battery < 15) { return PropertyMeasuredSeverity.SEVERITY_VERY_BAD;}
        else if(15 <= battery  && battery < 33) {return PropertyMeasuredSeverity.SEVERITY_BAD;}
        else if(33 <= battery  && battery < 50) {return PropertyMeasuredSeverity.SEVERITY_LITTLE_BAD;}
        else if(50 <= battery  && battery < 80) {return PropertyMeasuredSeverity.SEVERITY_AVERAGE;}
        else {return PropertyMeasuredSeverity.SEVERITY_GOOD;}
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

