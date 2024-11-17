class RulesFacadeMapper {
	
	constructor() {
		this.alarmSensorRuleMapper = new AlarmSensorRuleMapper();
		this.enableSensorRuleMapper = new EnableSensorRuleMapper();
		this.triggerActuatorSensorRuleMapper = new TriggerActuatorSensorRuleMapper();
		this.cameraSensorRuleMapper = new CameraSensorRuleMapper();
		this.outOfRangeGpsRuleMapper = new OutOfRangeGpsRuleMapper();
	}
	
	buildRulesFromServiceObject(sensorRulesServiceObject) {
		let alarmSensorRules = this.#buildAlarmSensorRulesFromServiceObject(sensorRulesServiceObject.sensorRules.alarmSensorRules);
		let enableSensorRules = this.#buildEnableSensorRulesFromServiceObject(sensorRulesServiceObject.sensorRules.enableSensorRules)
		let triggerActuatorSensorRules = this.#buildTriggerActuatorSensorRulesFromServiceObject(sensorRulesServiceObject.sensorRules.triggerActuatorSensorRules);
		let cameraSensorRules = this.#buildCameraSensorRulesFromServiceObject(sensorRulesServiceObject.sensorRules.cameraSensorRules);
		let outOfRangeGpsRules = this.#buildOutOfRangeGpsRulesFromServiceObject(sensorRulesServiceObject.gpsRules.outOfRangeGpsRules);
		let sensorRules = new SensorRules(alarmSensorRules, enableSensorRules, triggerActuatorSensorRules, cameraSensorRules);
		let gpsRules = new GpsRules(outOfRangeGpsRules);
		return new Rules(sensorRules, gpsRules);
	}	
	
	#buildOutOfRangeGpsRulesFromServiceObject(outOfRangeGpsRulesServiceObject){
		let outOfRangeGpsRules = [];
		outOfRangeGpsRulesServiceObject.forEach(outOfRangeGpsRuleServiceObject => {
			let outOfRangeGpsRule = this.buildOutOfRangeGpsRuleFromServiceObject(outOfRangeGpsRuleServiceObject);
			outOfRangeGpsRules.push(outOfRangeGpsRule);
		})
		return outOfRangeGpsRules;
	}
	
	buildOutOfRangeGpsRuleFromServiceObject(outOfRangeGpsRuleServiceObject) {
		return this.outOfRangeGpsRuleMapper.buildOutOfRangeGpsRuleFromServiceObject(outOfRangeGpsRuleServiceObject);
	}
	
	#buildAlarmSensorRulesFromServiceObject(alarmSensorRulesServiceObject) {
		let alarmSensorRules = [];
		alarmSensorRulesServiceObject.forEach(alarmSensorRuleServiceObject => {
			let alarmSensorRule = this.buildAlarmSensorRuleFromServiceObject(alarmSensorRuleServiceObject);
			alarmSensorRules.push(alarmSensorRule);
		})
		return alarmSensorRules;
	}
	
	buildAlarmSensorRuleFromServiceObject(alarmSensorRuleServiceObject) {
		return this.alarmSensorRuleMapper.buildAlarmSensorRuleFromServiceObject(alarmSensorRuleServiceObject);
	}
	
	#buildEnableSensorRulesFromServiceObject(enableSensorRulesServiceObject) {
		let enableSensorRules = [];
		enableSensorRulesServiceObject.forEach(enableSensorRuleServiceObject => {
			let enableSensorRule = this.buildEnableSensorRuleFromServiceObject(enableSensorRuleServiceObject);
			enableSensorRules.push(enableSensorRule);
		})
		return enableSensorRules;
	}
	
	buildEnableSensorRuleFromServiceObject(enableSensorRuleServiceObject){
		return this.enableSensorRuleMapper.buildEnableSensorRuleFromServiceObject(enableSensorRuleServiceObject);
	}
	
	
	#buildTriggerActuatorSensorRulesFromServiceObject(triggerActuatorSensorRulesServiceObject) {
		let triggerActuatorSensorRules = [];
		triggerActuatorSensorRulesServiceObject.forEach(triggerActuatorSensorRuleServiceObject => {
			let triggerActuatorSensorRule = this.buildTriggerActuatorSensorRuleFromServiceObject(triggerActuatorSensorRuleServiceObject);
			triggerActuatorSensorRules.push(triggerActuatorSensorRule);
		})
		return triggerActuatorSensorRules;
	}	
	
	buildTriggerActuatorSensorRuleFromServiceObject(triggerActuatorSensorRuleServiceObject) {
		 return this.triggerActuatorSensorRuleMapper.buildTriggerActuatorSensorRuleFromServiceObject(triggerActuatorSensorRuleServiceObject);
	}
	
	buildTriggerActuatorSensorRuleServiceObject(triggerActuatorSensorRule) {
		return this.triggerActuatorSensorRuleMapper.buildTriggerActuatorSensorRuleServiceObject(triggerActuatorSensorRule);
	}
	
	#buildCameraSensorRulesFromServiceObject(cameraSensorRulesServiceObject) {
		let cameraSensorRules = [];
		cameraSensorRulesServiceObject.forEach(cameraSensorRuleServiceObject => {
			let cameraSensorRule = this.buildCameraSensorRuleFromServiceObject(cameraSensorRuleServiceObject);
			cameraSensorRules.push(cameraSensorRule);
		})
		return cameraSensorRules;
	}
	
	buildCameraSensorRuleFromServiceObject(cameraSensorRuleServiceObject) {
		 return this.cameraSensorRuleMapper.buildCameraSensorRuleFromServiceObject(cameraSensorRuleServiceObject);
	}
	
	
	buildCameraSensorRuleServiceObject(cameraSensorRule) {
		return this.cameraSensorRuleMapper.buildCameraSensorRuleServiceObject(cameraSensorRule);
	}
	
	buildAlarmSensorRuleServiceObject(alarmSensorRule) {
		return this.alarmSensorRuleMapper.buildAlarmSensorRuleServiceObject(alarmSensorRule);
	}
	
	buildEnableSensorRuleServiceObject(enableSensorRule) {
		return this.enableSensorRuleMapper.buildEnableSensorRuleServiceObject(enableSensorRule);
	}
	
	buildOutOfRangeGpsRuleServiceObject(outOfRangeGpsRule) {
		return this.outOfRangeGpsRuleMapper.buildOutOfRangeGpsRuleServiceObject(outOfRangeGpsRule);
	}
		
	
}

	