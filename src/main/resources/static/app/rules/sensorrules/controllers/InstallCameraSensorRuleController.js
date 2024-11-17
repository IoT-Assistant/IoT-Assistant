
sensorRulesModule.controller ("InstallCameraSensorRuleController", function(RuleAPIService, CameraAPIService){
	
	let self = this;
	
	self.cameras = [];

	self.cameraName;			
	
	let fetchCameras = function(){
		CameraAPIService.getCameras()
		.then(function(cameras) { 
			self.cameras = cameras;	
		},function() {
			self.cameras = [];
		})
	}
	
	let initializeController = function() {
		fetchCameras();
	}
	
	initializeController();
	
	self.allRequired = function(sensorRuleSettings) {
		let cameraSensorRule = buildCameraSensorRule(sensorRuleSettings, self.cameraName);
		return cameraSensorRule.isValid();
	}
	
	let buildCameraSensorRule = function(sensorRuleSettings, cameraName) {
		let sensorMeasureThresholdSettings = sensorRuleSettings.sensorMeasureThresholdSettings;
		let timeBetweenTriggers = sensorRuleSettings.timeBetweenTriggers;
		let notificationType = sensorRuleSettings.notificationType;
		let enabled = true;
		return new CameraSensorRule(sensorMeasureThresholdSettings, null, enabled, timeBetweenTriggers, notificationType, cameraName);
	}
	

	self.install = function(sensorRuleSettings) {
		let cameraSensorRule =  buildCameraSensorRule(sensorRuleSettings,  self.cameraName);
		let promise = RuleAPIService.installCameraSensorRule(cameraSensorRule);	
		return promise;
	}

});