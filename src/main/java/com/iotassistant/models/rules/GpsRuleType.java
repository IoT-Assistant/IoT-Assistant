package com.iotassistant.models.rules;

import java.util.ArrayList;
import java.util.List;

public enum GpsRuleType {
	
	OUT_OF_RANGE("Out Of Range");
	
	private String string;

	private GpsRuleType(String string) {
		this.string = string;
	}

	public static List<String> getAllInstances() {
		List<String> allInstancesStrings = new ArrayList<String>();
		for (GpsRuleType type : GpsRuleType.values()) {
			allInstancesStrings.add(type.getString());
		}
		return allInstancesStrings;
	}

	public String getString() {
		return string;
	}

}
