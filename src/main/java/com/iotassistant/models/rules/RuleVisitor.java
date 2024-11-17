package com.iotassistant.models.rules;

public interface RuleVisitor {

	void visit(SensorRule sensorRule);
	
	void visit(GpsRule gpsRule);

}
