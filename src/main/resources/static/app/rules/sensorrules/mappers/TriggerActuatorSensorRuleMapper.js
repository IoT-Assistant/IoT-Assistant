class TriggerActuatorSensorRuleMapper {
	
	constructor() {
		  this.sensorRuleMapper = new SensorRuleMapper();
	}
	
	buildTriggerActuatorSensorRuleFromServiceObject(triggerActuatorSensorRuleServiceObject) {
		let measureThresholdSettingsServiceObject = triggerActuatorSensorRuleServiceObject.sensorMeasureThresholdSettings;
		let measureThresholdSettings = this.sensorRuleMapper.getMeasureThresholdSettingsFromServiceObject(measureThresholdSettingsServiceObject);
		let id = this.sensorRuleMapper.getSensorRuleIdFromServiceObject(triggerActuatorSensorRuleServiceObject);
		let enabled = this.sensorRuleMapper.getSensorRuleEnabledFromServiceObject(triggerActuatorSensorRuleServiceObject);
		let timeBetweenTriggers = this.sensorRuleMapper.getSensorRuleTimeBetweenTriggersFromServiceObject(triggerActuatorSensorRuleServiceObject);
		let notificationType = this.sensorRuleMapper.getSensorRuleNotificationTypeFromServiceObject(triggerActuatorSensorRuleServiceObject);
		let actuatorSettings = this.#buildActuatorSetValue(triggerActuatorSensorRuleServiceObject);
		let triggerActuatorSensorRule = new TriggerActuatorSensorRule(measureThresholdSettings, id, enabled, timeBetweenTriggers, notificationType, actuatorSettings);
		return triggerActuatorSensorRule;
	}
	
	#buildActuatorSetValue(triggerActuatorSensorRuleServiceObject) {
		let actuatorSettings = new ActuatorSetValueSettings();
		actuatorSettings.setActuatorName(triggerActuatorSensorRuleServiceObject.actuatorName);
		let actuatorProperty = PropertyMapper.map(triggerActuatorSensorRuleServiceObject.actuatorProperty);
		actuatorSettings.setActuatorProperty(actuatorProperty);
		actuatorSettings.setActuatorSetValue(triggerActuatorSensorRuleServiceObject.actuatorSetValue);
		return actuatorSettings;

	}
	
	buildTriggerActuatorSensorRuleServiceObject(triggerActuatorSensorRule) {
		let triggerActuatorSensorRuleServiceObject =  this.sensorRuleMapper.buildSensorRuleServiceObject(triggerActuatorSensorRule);
		triggerActuatorSensorRuleServiceObject.actuatorName = triggerActuatorSensorRule.actuatorSettings.actuatorName;
		triggerActuatorSensorRuleServiceObject.actuatorProperty = triggerActuatorSensorRule.actuatorSettings.actuatorProperty;
		triggerActuatorSensorRuleServiceObject.actuatorSetValue = triggerActuatorSensorRule.actuatorSettings.actuatorSetValue;
		return triggerActuatorSensorRuleServiceObject;
	}
	

	
}