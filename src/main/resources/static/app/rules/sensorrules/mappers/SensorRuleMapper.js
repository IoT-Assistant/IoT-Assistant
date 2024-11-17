class SensorRuleMapper {
	
	constructor() {
		this.ruleMapper = new RuleMapper();
	}
	
	
	getMeasureThresholdSettingsFromServiceObject(measureThresholdSettingsObject) {
	 	let sensorMeasureThresholdSettings = new SensorMeasureThresholdSettings();
		sensorMeasureThresholdSettings.setSensorName(measureThresholdSettingsObject.sensorName);
		let sensorProperty = PropertyMapper.map(measureThresholdSettingsObject.sensorProperty);
		sensorMeasureThresholdSettings.setSensorProperty(sensorProperty);
		sensorMeasureThresholdSettings.setSensorValueThreshold(measureThresholdSettingsObject.sensorValueThreshold);
		sensorMeasureThresholdSettings.setSensorAnalogThresholdOperator(measureThresholdSettingsObject.sensorAnalogThresholdOperator);
		return sensorMeasureThresholdSettings;
	}
	
	
	getSensorRuleIdFromServiceObject(sensorRuleServiceObject) {
		return this.ruleMapper.getIdFromServiceObject(sensorRuleServiceObject);
	}
	
	getSensorRuleEnabledFromServiceObject(sensorRuleServiceObject) {
		return this.ruleMapper.getEnabledFromServiceObject(sensorRuleServiceObject);
	}
	
	getSensorRuleTimeBetweenTriggersFromServiceObject(sensorRuleServiceObject) {
		return this.ruleMapper.getTimeBetweenTriggersFromServiceObject(sensorRuleServiceObject);;
	}
	
	getSensorRuleNotificationTypeFromServiceObject(sensorRuleServiceObject) {
		return this.ruleMapper.getNotificationTypeFromServiceObject(sensorRuleServiceObject);
	}
	
	buildSensorRuleServiceObject(sensorRule) {
		let sensorRuleServiceObject = this.ruleMapper.buildRuleServiceObject(sensorRule);
		sensorRuleServiceObject.sensorMeasureThresholdSettings = this.#buildSensorMeasureThresholdSettingsServiceObject(sensorRule);		
		return sensorRuleServiceObject;
	}
	
	#buildSensorMeasureThresholdSettingsServiceObject(sensorRule) {
		let sensorMeasureThresholdSettingsObject = sensorRule.sensorMeasureThresholdSettings;
		sensorMeasureThresholdSettingsObject.sensorName = sensorRule.getSensorName();
		sensorMeasureThresholdSettingsObject.sensorProperty = sensorRule.getSensorProperty();
		sensorMeasureThresholdSettingsObject.sensorValueThreshold = sensorRule.getSensorValueThreshold();
		sensorMeasureThresholdSettingsObject.sensorAnalogThresholdOperator = sensorRule.getSensorAnalogThresholdOperator();
		return sensorMeasureThresholdSettingsObject;
	}
	
}