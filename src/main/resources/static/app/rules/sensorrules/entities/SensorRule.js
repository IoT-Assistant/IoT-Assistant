class SensorRule extends Rule {
	
	
	constructor(sensorMeasureThresholdSettings, id, enabled, timeBetweenTriggers, notificationType) {
		super(id, enabled, timeBetweenTriggers, notificationType);
		this.sensorMeasureThresholdSettings = sensorMeasureThresholdSettings;
	}

	getSensorName() {
		return this.sensorMeasureThresholdSettings.getSensorName();
	}
	
	
	getSensorAnalogThresholdOperator() {
		return this.sensorMeasureThresholdSettings.getSensorAnalogThresholdOperator();
	}
	
	setSensorAnalogThresholdOperator(analogThresholdOperator) {
		return this.sensorMeasureThresholdSettings.setSensorAnalogThresholdOperator(analogThresholdOperator);
	}
	
	
	getSensorValueThreshold() {
		return this.sensorMeasureThresholdSettings.getSensorValueThreshold();
	}
	
	getSensorValueThresholdString() {
		return this.sensorMeasureThresholdSettings.getSensorValueThresholdString();
	}
	

	getSensorPropertyMinimumValue() {
		return this.sensorMeasureThresholdSettings.getSensorPropertyMinimumValue();
	}
	
	getSensorPropertyMaximumValue() {
		return this.sensorMeasureThresholdSettings.getSensorPropertyMaximumValue();
	}
	
	
	isSensorPropertyBinary() {
		return this.sensorMeasureThresholdSettings.isSensorPropertyBinary();
	}
	
	getSensorProperty() {
		return this.sensorMeasureThresholdSettings.getSensorProperty() ;
	}
	
	setSensorProperty(sensorProperty) {
		this.sensorMeasureThresholdSettings.setSensorProperty(sensorProperty);
	}
	
	
	getSensorPropertyName() {
		return this.sensorMeasureThresholdSettings.getSensorPropertyName() ;
	}
	
	isValid() {
		return super.isValid() &&  this.sensorMeasureThresholdSettings.isValid();
	}
	
}