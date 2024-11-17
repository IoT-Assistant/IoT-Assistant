
rulesModule.service("RuleAPIService",function(RestAPIService, $q){
	let self = this;	
	
	let ruleBaseUri = "rules/";
	
	let sensorRulesBaseUri = ruleBaseUri + 'sensorRules/';
	
	let rulesMapper = new RulesFacadeMapper();
	
	
	self.getRules = function () {
		let deferred = $q.defer();
		RestAPIService.get(ruleBaseUri).then(function(rulesServiceObject) {
			deferred.resolve(rulesMapper.buildRulesFromServiceObject(rulesServiceObject));
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}

	
	self.installTriggerActuatorSensorRule = function (triggerActuatorSensorRule) {
		let deferred = $q.defer();
		let triggerActuatorSensorRuleServiceObject = rulesMapper.buildTriggerActuatorSensorRuleServiceObject(triggerActuatorSensorRule);
		RestAPIService.post(ruleBaseUri + 'triggerActuatorSensorRules/', triggerActuatorSensorRuleServiceObject).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	self.installAlarmSensorRule = function (alarmSensorRule) {
		let deferred = $q.defer();
		let alarmSensorRuleServiceObject = rulesMapper.buildAlarmSensorRuleServiceObject(alarmSensorRule);
		RestAPIService.post(ruleBaseUri + 'alarmSensorRules/', alarmSensorRuleServiceObject).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	

	
	self.installEnableSensorRule = function (enableSensorRule) {
		let deferred = $q.defer();
		let enableSensorRuleServiceObject = rulesMapper.buildEnableSensorRuleServiceObject(enableSensorRule);
		RestAPIService.post(ruleBaseUri + 'enableRuleSensorRules/', enableSensorRuleServiceObject).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	self.installCameraSensorRule = function (cameraSensorRule) {
		let deferred = $q.defer();
		let cameraSensorRuleServiceObject = rulesMapper.buildCameraSensorRuleServiceObject(cameraSensorRule);
		RestAPIService.post(ruleBaseUri + 'cameraSensorRules/', cameraSensorRuleServiceObject).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	self.installOutOfRangeGpsRule = function (outOfRangeGpsRule) {
		let deferred = $q.defer();
		let outOfRangeGpsRuleServiceObject = rulesMapper.buildOutOfRangeGpsRuleServiceObject(outOfRangeGpsRule);
		RestAPIService.post(ruleBaseUri + 'outOfRangeGpsRules/', outOfRangeGpsRuleServiceObject).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	self.deleteSensorRule = function (ruleId) {
		let deferred = $q.defer();
		RestAPIService.delete(ruleBaseUri.concat(ruleId)).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	self.enableRule = function (enable, ruleId) {
		let deferred = $q.defer();
		let enableServiceObject = {};
		enableServiceObject.enable = enable;
		RestAPIService.patch(ruleBaseUri.concat(ruleId), enableServiceObject).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	

});

