
sensorRulesModule.controller ("InstallAlarmSensorRuleController", function(RuleAPIService){
	
	let self = this;
	
	self.allRequired = function(sensorRuleSettings) {
		let alarmSensorRule = buildAlarmSensorRule(sensorRuleSettings);
		return alarmSensorRule.isValid();
	}
	
	let buildAlarmSensorRule = function(sensorRuleSettings) {
		let sensorMeasureThresholdSettings = sensorRuleSettings.sensorMeasureThresholdSettings;
		let timeBetweenTriggers = sensorRuleSettings.timeBetweenTriggers;
		let notificationType = sensorRuleSettings.notificationType;
		return new AlarmSensorRule(sensorMeasureThresholdSettings, null, true, timeBetweenTriggers, notificationType);
	}
	

	self.install = function(sensorRuleSettings) {
		let alarmSensorRule = buildAlarmSensorRule(sensorRuleSettings);
		let promise = RuleAPIService.installAlarmSensorRule(alarmSensorRule);	
		return promise;
	}

});