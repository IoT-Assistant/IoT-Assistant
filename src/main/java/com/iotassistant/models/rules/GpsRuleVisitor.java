package com.iotassistant.models.rules;

public interface GpsRuleVisitor {

	void visit(OutOfRangeGpsRule outOfRangeGpsRule);

}
