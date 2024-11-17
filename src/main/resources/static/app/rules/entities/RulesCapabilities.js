class RulesCapabilities {

	constructor(supportedSensorRulesTypes, supportedGpsRulesTypes, supportedRulesTimeBetweenTriggers) {
		this.supportedSensorRulesTypes = supportedSensorRulesTypes;
		this.supportedGpsRulesTypes = supportedGpsRulesTypes;
		this.supportedRulesTimeBetweenTriggers = supportedRulesTimeBetweenTriggers;
	}

	getSupportedSensorRulesTypes() {
		return this.supportedSensorRulesTypes;
	}
	
	getSupportedGpsRulesTypes() {
		return this.supportedGpsRulesTypes;
	}
	
	getSupportedRulesTimeBetweenTriggers() {
		return this.supportedRulesTimeBetweenTriggers;
	}
	
}