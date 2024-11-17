class Rule {
		
	constructor(id, enabled, timeBetweenTriggers, notificationType) {
		this.id = id;		
		this.enabled = enabled;
		this.timeBetweenTriggers = timeBetweenTriggers;
		this.notificationType = notificationType;
	}

	isValid() {
		var stateIsValid = false;
		if (this.enabled != null && this.notificationType != null && this.timeBetweenTriggers != null) {
			stateIsValid = true;
		}
		return stateIsValid;
	}
	
	setId(id) {
		this.id = id;
	}
	
	getId() {
		return this.id;
	}
	
	getTimeBetweenTriggers() {
		return this.timeBetweenTriggers;
	}
	
	setTimeBetweenTriggers(timeBetweenTriggers) {
		this.timeBetweenTriggers = timeBetweenTriggers;
	}
	
	getNotificationType() {
		return this.notificationType;
	}
	
	isEnabled() {
		return this.enabled;
	}
	
	setEnabled(enabled) {
	 	this.enabled = enabled;
	}

}