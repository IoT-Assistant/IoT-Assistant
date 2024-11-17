class AlarmSensorRuleMapper {
	
	constructor() {
		  this.sensorRuleMapper = new SensorRuleMapper();
	}
	
	buildAlarmSensorRuleFromServiceObject(alarmSensorRuleServiceObject) {
		var measureThresholdSettingsServiceObject = alarmSensorRuleServiceObject.sensorMeasureThresholdSettings;
		var measureThresholdSettings = this.sensorRuleMapper.getMeasureThresholdSettingsFromServiceObject(measureThresholdSettingsServiceObject);
		var id = this.sensorRuleMapper.getSensorRuleIdFromServiceObject(alarmSensorRuleServiceObject);
		var enabled = this.sensorRuleMapper.getSensorRuleEnabledFromServiceObject(alarmSensorRuleServiceObject);
		var timeBetweenTriggers = this.sensorRuleMapper.getSensorRuleTimeBetweenTriggersFromServiceObject(alarmSensorRuleServiceObject);
		var notificationType = this.sensorRuleMapper.getSensorRuleNotificationTypeFromServiceObject(alarmSensorRuleServiceObject);
		var alarmSensorRule = new AlarmSensorRule(measureThresholdSettings, id, enabled, timeBetweenTriggers, notificationType);
		return alarmSensorRule;
	}
	
	buildAlarmSensorRuleServiceObject(alarmSensorRule) {
		var alarmSensorRuleServiceObject =  this.sensorRuleMapper.buildSensorRuleServiceObject(alarmSensorRule);
		return alarmSensorRuleServiceObject;
	}
	
}