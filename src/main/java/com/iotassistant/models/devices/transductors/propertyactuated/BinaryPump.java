package com.iotassistant.models.devices.transductors.propertyactuated;

public class BinaryPump extends BinaryPropertyActuated{
	
	private static final String NAME = "Pump";

	@Override
	public String getName() {
		return NAME;
	}

}