class GpsRule extends Rule {
	
	
	constructor(gpsName, id, enabled, timeBetweenTriggers, notificationType) {
		super(id, enabled, timeBetweenTriggers, notificationType);
		this.gpsName = gpsName;
	}

	getGpsName() {
		return this.gpsName;
	}
	
	
	isValid() {
		return super.isValid() &&  this.gpsName !== 'undefined';
	}
	
}