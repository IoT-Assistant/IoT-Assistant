
sensorRulesModule.controller ("InstallEnableRuleSensorRuleController", function(RuleAPIService){
	
	let self = this;

	self.ruleId;
	
	self.enableAction;
	
	self.rules = [];
	
	let fetchRules = function(){
		RuleAPIService.getRules()
		.then(function(rules) { 
			self.rules = rules.getAllRules();
		},function() {
			self.rules = [];
		})
	}
	
	let initializeController = function() {
		fetchRules();
	}
	
	initializeController();
	
	self.allRequired = function(sensorRuleSettings) {
		let enableRuleSensorRule = buildEnableRuleSensorRule(sensorRuleSettings);
		return enableRuleSensorRule.isValid();
	}
	
	let buildEnableRuleSensorRule = function(sensorRuleSettings) {
		let sensorMeasureThresholdSettings = sensorRuleSettings.sensorMeasureThresholdSettings;
		let timeBetweenTriggers = sensorRuleSettings.timeBetweenTriggers;
		let notificationType = sensorRuleSettings.notificationType;
		let enabled = true;
		return new EnableRuleSensorRule(sensorMeasureThresholdSettings, null, enabled, timeBetweenTriggers, notificationType, self.ruleId, self.enableAction);
	}
	

	self.install  = function(sensorRuleSettings) {
		let enableSensorRule =  buildEnableRuleSensorRule(sensorRuleSettings);
		let promise = RuleAPIService.installEnableSensorRule(enableSensorRule);	
		return promise;
	}

});