package com.iotassistant.models;

public enum AnalogThresholdOperatorEnum {
	EQUAL_OR_GREATER_THAN(">="),
	LESS_THAN("<"),
	EQUAL_THAN("=");
	
	private String string;
	
	AnalogThresholdOperatorEnum(String string) {
		this.string = string;
	}

	public String toString() {
		return string;
	}
	
	public boolean isThresholdReached(Float thresholdValue, Float analogValue) {
		boolean isThresholdReached = false;
		if (this.equals(EQUAL_OR_GREATER_THAN) && analogValue >= thresholdValue) {
			isThresholdReached = true;
		}
		else if (this.equals(LESS_THAN) && analogValue < thresholdValue) {
			isThresholdReached = true;
		}
		else if (this.equals(EQUAL_THAN) && Float.compare(analogValue, thresholdValue) == 0) {
			isThresholdReached = true;
		}
		return isThresholdReached;
	}
	
	public static AnalogThresholdOperatorEnum getInstance(String string) {
		for (AnalogThresholdOperatorEnum alarmNotificationBetweenTime : AnalogThresholdOperatorEnum.values()) {
			if (alarmNotificationBetweenTime.toString().equals(string)) {
				return alarmNotificationBetweenTime;
			}
		}
		return null;
	}

}
