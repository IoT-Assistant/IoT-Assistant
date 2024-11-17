package com.iotassistant.controllers.dtos;

import com.iotassistant.models.rules.SensorRule;

class SensorRuleDTO extends RuleDTO{
	
	SensorMeasureThresholdSettingsDTO sensorMeasureThresholdSettings;

	public SensorRuleDTO() {
		super();
	}


	SensorRuleDTO(SensorRule sensorRule) {
		super(sensorRule);
		this.sensorMeasureThresholdSettings = new SensorMeasureThresholdSettingsDTO(sensorRule.getSensorMeasureThresholdSettings());
	}

	public SensorMeasureThresholdSettingsDTO getSensorMeasureThresholdSettings() {
		return sensorMeasureThresholdSettings;
	}

	public void setSensorMeasureThresholdSettings(SensorMeasureThresholdSettingsDTO sensorMeasureThresholdSettings) {
		this.sensorMeasureThresholdSettings = sensorMeasureThresholdSettings;
	}

}
