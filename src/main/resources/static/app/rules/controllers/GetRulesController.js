
rulesModule.controller("GetRulesController",function($scope, RuleAPIService, SweetAlertService, $interval){

	const RULES_REFRESH_TIME_MS = 5000 ;

	var self = this;
	
	self.alarmSensorRules = [];
	
	self.cameraSensorRules = [];
	
	self.enableRuleSensorRules = [];
	
	self.triggerActuatorSensorRules = [];
	
	getRules = function(){		
		RuleAPIService.getRules()
		.then(function(rules) { 
			self.alarmSensorRules = rules.getAlarmSensorRules();
			self.enableRuleSensorRules = rules.getEnableSensorRules();
			self.triggerActuatorSensorRules = rules.getTriggerActuatorSensorRules();
			self.cameraSensorRules = rules.getCameraSensorRules();
			self.outOfRangeGpsRules = rules.getOutOfRangeGpsRules();
		},function() {
		})
	}
	
	var initializeController = function() {
		getRules();   
		var refreshRulesInterval = $interval(getRules, RULES_REFRESH_TIME_MS);
		$scope.$on('$destroy',function(){
			if(refreshRulesInterval) {
				$interval.cancel(refreshRulesInterval);
			}
		})
	}	
	
	initializeController();

	
	self.enableSensorRule = function(enable, ruleId){
		enableString = (enable ? 'enabled' : 'disabled') ;
		RuleAPIService.enableRule(enable, ruleId)
		.then(function() { 
			SweetAlertService.showSuccessAlert('Sensor rule ' + enableString + ' with success');
		},function() {
			SweetAlertService.showErrorAlert('Sensor rule could not be ' + enableString);
		})
	}
	
	
	self.deleteSensorRule = function(sensorRuleId){
		function deleteRule() {
			RuleAPIService.deleteSensorRule(sensorRuleId)
			.then(function() { 
				SweetAlertService.showSuccessAlert('Sensor rule deleted with success');
			},function() {
				SweetAlertService.showErrorAlert('Sensor rule deletion failed');
			})
		}
		SweetAlertService.showWarningWithCallback('Are you sure you want to delete sensor rule ' + sensorRuleId + '?', deleteRule);
	}
	

});
