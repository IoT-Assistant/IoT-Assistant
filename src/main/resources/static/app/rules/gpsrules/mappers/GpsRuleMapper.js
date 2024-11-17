class GpsRuleMapper {
	
	constructor() {
		this.ruleMapper = new RuleMapper();
	}
	
	
	getGpsRuleGpsNameFromServiceObject(gpsRuleServiceObject) {
		return gpsRuleServiceObject.gpsName;
	}
	
	getGpsRuleIdFromServiceObject(gpsRuleServiceObject) {
		return this.ruleMapper.getIdFromServiceObject(gpsRuleServiceObject);
	}
	
	getGpsRuleEnabledFromServiceObject(gpsRuleServiceObject) {
		return this.ruleMapper.getEnabledFromServiceObject(gpsRuleServiceObject);
	}
	
	getGpsRuleTimeBetweenTriggersFromServiceObject(gpsRuleServiceObject) {
		return this.ruleMapper.getTimeBetweenTriggersFromServiceObject(gpsRuleServiceObject);;
	}
	
	getGpsRuleNotificationTypeFromServiceObject(gpsRuleServiceObject) {
		return this.ruleMapper.getNotificationTypeFromServiceObject(gpsRuleServiceObject);
	}
	
	buildGpsRuleServiceObject(gpsRule) {
		let gpsRuleServiceObject = this.ruleMapper.buildRuleServiceObject(gpsRule);
		gpsRuleServiceObject.gpsName = gpsRule.gpsName;		
		return gpsRuleServiceObject;
	}
	
	
}