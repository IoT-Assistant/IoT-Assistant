class  SensorRuleNotification extends Notification{
	
	
	constructor(id, sensorRule, sensorValue, date) {
		super(id, date);
		this.sensorRule = sensorRule;
		this.sensorValue = sensorValue;
	}

	toString() {
		var sensorValue = this.sensorValue;
		var sensorRule = this.sensorRule;
		var sensorValue = this.sensorValue
		var analogThresholdOperator = sensorRule.isSensorPropertyBinary() ? '' : sensorRule.getSensorAnalogThresholdOperator();
		var text =  "#" + sensorRule.getId() + " Notification: "  + ": " + "Sensor " + sensorRule.getSensorName() + " with property " 
		+ sensorRule.getSensorPropertyName() +  " reached threshold " + analogThresholdOperator + sensorRule.getSensorValueThreshold() + ". Sensor value was " + sensorValue
		+ ". Date: " + this.date;
		return text;
	}


	
}