
sensorRulesModule.controller ("InstallSensorRuleController", function($scope, $controller, SystemAPIService, SensorAPIService, SweetAlertService, $route){

	let self = this;
	
	self.sensors = [];
	
	self.sensorRuleSettings = {sensorMeasureThresholdSettings: new SensorMeasureThresholdSettings(), sensorRuleType : null, 
							   timeBetweenTriggers: null, notificationType: null};

	
	self.sensorPropertiesOptions =  [];
	
	self.installAlarmSensorRuleController = $controller('InstallAlarmSensorRuleController', {$scope: $scope.$new()});

	self.installEnableRuleSensorRuleController = $controller('InstallEnableRuleSensorRuleController', {$scope: $scope.$new()});
	
	self.installTriggerActuatorSensorRuleController = $controller('InstallTriggerActuatorSensorRuleController', {$scope: $scope.$new()});
	
	self.installCameraSensorRuleController = $controller('InstallCameraSensorRuleController', {$scope: $scope.$new()});
	
	self.rulesCapabilities;
	
	self.supportedNotificationsTypes;
	
	let fetchSensors = function(){
		SensorAPIService.getSensors()
		.then(function(sensors) { 
			self.sensors = sensors;
		},function() {
			self.sensors = [];
		})
	}
	
	let fetchSensorRulesCapabilities = function(){
		SystemAPIService.getRulesCapabilities()
		.then(function(rulesCapabilities) { 
			self.rulesCapabilities = rulesCapabilities;
		},function() {
		})
	}
	
	let fetchNotificationsCapabilities = function(){
		SystemAPIService.getNotificationsCapabilities()
		.then(function(notificationsCapabilities) { 
			self.supportedNotificationsTypes = notificationsCapabilities.getSupportedNotificationsTypes();
		},function() {
		})
	}
	
	let initializeController = function() {
		fetchSensors();
		fetchSensorRulesCapabilities();
		fetchNotificationsCapabilities();
	}
	
	initializeController();
	
	self.updateSensorPropertiesOptions = function(){		
		self.sensors.forEach(sensor  => {
			if (sensor.getName() == self.sensorRuleSettings.sensorMeasureThresholdSettings.getSensorName()) {
				self.sensorPropertiesOptions = sensor.getProperties();
			}
		})
	}
	
	self.setSensorRuleType = function(sensorRuleType) {
		self.sensorRuleSettings.sensorRuleType = sensorRuleType;
	}
	
	
	self.isSensorPropertySelected = function() {
		let sensorProperty = self.sensorRuleSettings.sensorMeasureThresholdSettings.sensorProperty;
		return sensorProperty != null && 'name' in sensorProperty;
	}
	
	self.allRequired = function() {
		if (self.sensorRuleSettings.sensorRuleType != null) {
			return getSensorRuleController().allRequired(self.sensorRuleSettings);
		}	
		return false;
	}
	
	
	let getSensorRuleController = function() {
		if (self.isAlarmSensorRuleTypeSelected()) {
				return self.installAlarmSensorRuleController;
		}
		if (self.isEnableSensorRuleTypeSelected()) {
				return self.installEnableRuleSensorRuleController;
		}
		if (self.isTriggerActuatorSensorRuleTypeSelected()) {
				return self.installTriggerActuatorSensorRuleController;
		}
		if (self.isCameraSensorRuleTypeSelected()) {
				return self.installCameraSensorRuleController;
		}
	}
	
	self.isAlarmSensorRuleTypeSelected = function() {
		return self.sensorRuleSettings.sensorRuleType == AlarmSensorRule.alarmSensorRuleType;
	}
	
	self.isEnableSensorRuleTypeSelected = function() {
		return self.sensorRuleSettings.sensorRuleType == EnableRuleSensorRule.enableRuleSensorRuleType;
	}
	
	self.isTriggerActuatorSensorRuleTypeSelected = function() {
		return self.sensorRuleSettings.sensorRuleType == TriggerActuatorSensorRule.triggerActuatorSensorRuleType;
	}
	
	self.isCameraSensorRuleTypeSelected = function() {
		return self.sensorRuleSettings.sensorRuleType == CameraSensorRule.cameraSensorRuleType;
	}

	self.installAndRedirect = function() {
		let sensorRuleController = getSensorRuleController(self.sensorRuleSettings.sensorRuleType);	
		let promise = sensorRuleController.install(self.sensorRuleSettings);
		promise.then(function() {
			let redirectURL = $route.current.$$route.redirectURL;
			SweetAlertService.showSuccessAlertAndRedirect('Sensor rule installed with success', redirectURL);
		},function(error) {
			SweetAlertService.showErrorAlert('Sensor rule installation failed' + ' \n Error: ' + error.data.message);
		})		
	}

});
