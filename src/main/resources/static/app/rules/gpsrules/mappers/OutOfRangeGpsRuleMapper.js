class OutOfRangeGpsRuleMapper {
	
	constructor() {
		  this.gpsRuleMapper = new GpsRuleMapper();
	}
	
	buildOutOfRangeGpsRuleFromServiceObject(outOfRangeGpsRuleServiceObject) {
		let gpsName = this.gpsRuleMapper.getGpsRuleGpsNameFromServiceObject(outOfRangeGpsRuleServiceObject);
		let id = this.gpsRuleMapper.getGpsRuleIdFromServiceObject(outOfRangeGpsRuleServiceObject);
		let enabled = this.gpsRuleMapper.getGpsRuleEnabledFromServiceObject(outOfRangeGpsRuleServiceObject);
		let timeBetweenTriggers = this.gpsRuleMapper.getGpsRuleTimeBetweenTriggersFromServiceObject(outOfRangeGpsRuleServiceObject);
		let notificationType = this.gpsRuleMapper.getGpsRuleNotificationTypeFromServiceObject(outOfRangeGpsRuleServiceObject);
		let outOfRangeGpsRule = new OutOfRangeGpsRule(outOfRangeGpsRuleServiceObject.latitude, outOfRangeGpsRuleServiceObject.longitude, outOfRangeGpsRuleServiceObject.range, gpsName, id, enabled, timeBetweenTriggers, notificationType);
		return outOfRangeGpsRule;
	}
	
	buildOutOfRangeGpsRuleServiceObject(outOfRangeGpsRule) {
		let outOfRangeGpsRuleServiceObject =  this.gpsRuleMapper.buildGpsRuleServiceObject(outOfRangeGpsRule);
		outOfRangeGpsRuleServiceObject.latitude = outOfRangeGpsRule.getLatitude();
		outOfRangeGpsRuleServiceObject.longitude = outOfRangeGpsRule.getLongitude();
		outOfRangeGpsRuleServiceObject.range = outOfRangeGpsRule.getRange();
		return outOfRangeGpsRuleServiceObject;
	}
	
}