class Rules {
	
	constructor(sensorRules, gpsRules) {
		this.sensorRules = sensorRules;
		this.gpsRules = gpsRules;
	}
	
	getAlarmSensorRules() {
		return this.sensorRules.getAlarmSensorRules();
	}
	
	getEnableSensorRules() {
		return this.sensorRules.getEnableSensorRules();
	}
	
	getTriggerActuatorSensorRules() {
		return this.sensorRules.getTriggerActuatorSensorRules();
	}
	
	getCameraSensorRules() {
		return this.sensorRules.getCameraSensorRules();
	}
	
	getOutOfRangeGpsRules() {
		return this.gpsRules.getOutOfRangeGpsRules();
	}
	
	getAllRules() {
		let allRules = this.sensorRules.getAllSensorRules();
		allRules = allRules.concat(this.gpsRules.getAllGpsRules());
		return allRules;
	}
	
}