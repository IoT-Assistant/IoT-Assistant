class RuleMapper {
	
	constructor() {
	}
	
	getIdFromServiceObject(ruleServiceObject) {
		return ruleServiceObject.id;
	}
	
	getEnabledFromServiceObject(ruleServiceObject) {
		return ruleServiceObject.enabled;
	}
	
	getTimeBetweenTriggersFromServiceObject(ruleServiceObject) {
		return ruleServiceObject.timeBetweenTriggers;
	}
	
	getNotificationTypeFromServiceObject(ruleServiceObject) {
		return ruleServiceObject.notificationType;
	}
	
	buildRuleServiceObject(rule) {
		let ruleServiceObject = {};
		ruleServiceObject.timeBetweenTriggers = rule.getTimeBetweenTriggers();
		ruleServiceObject.notificationType = rule.getNotificationType();
		ruleServiceObject.enabled = rule.isEnabled();
		return ruleServiceObject;
	}
	

}