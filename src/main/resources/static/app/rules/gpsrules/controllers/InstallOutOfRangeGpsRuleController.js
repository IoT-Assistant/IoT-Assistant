gpsRulesModule.controller ("InstallOutOfRangeGpsRuleController", function(RuleAPIService){
	
	let self = this;
	
	self.latitude = null;
	
	self.longitude = null;
	
	self.range = null;
	
	self.allRequired = function(gpsRule) {
		let outOfRangeGpsRule = buildOutOfRangeGpsRule(gpsRule);
		return outOfRangeGpsRule.isValid();
	}
	
	let buildOutOfRangeGpsRule = function(gpsRule) {
		return new OutOfRangeGpsRule(self.latitude, self.longitude, self.range, gpsRule.gpsName, null, true, gpsRule.timeBetweenTriggers, gpsRule.notificationType);
	}
	

	self.install = function(gpsRule) {
		let outOfRangeGpsRule = buildOutOfRangeGpsRule(gpsRule);
		return RuleAPIService.installOutOfRangeGpsRule(outOfRangeGpsRule);	
	}

});