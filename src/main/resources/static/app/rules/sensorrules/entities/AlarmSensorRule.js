class AlarmSensorRule extends SensorRule{
		
	
	constructor(sensorMeasureThresholdSettings, id, enabled, timeBetweenTriggers, notificationType) {
		super(sensorMeasureThresholdSettings, id, enabled, timeBetweenTriggers, notificationType);
	}
	
	isValid() {
		return super.isValid();
	}
	
	accept(sensorRuleVisitor) {
		sensorRuleVisitor.visitAlarmSensorRule(this);
	}
	
	static get alarmSensorRuleType() {
    	return 'Sensor alarm';
  	}

}