class OutOfRangeGpsRule extends GpsRule {
	
	constructor(latitude, longitude, range, gpsName, id, enabled, timeBetweenTriggers, notificationType) {
		super(gpsName, id, enabled, timeBetweenTriggers, notificationType);
		this.gpsName = gpsName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.range = range;
	}
	
	getLatitude() {
		return this.latitude;
	}
	
	getLongitude() {
		return this.longitude;
	}
	
	getRange() {
		return this.range;
	}
	
	isValid() {
		return super.isValid() &&  this.positionIsValid() && 0 < this.range;
	}
	
	positionIsValid() {
		return this.longitude !== 'undefined' && -180 <= this.longitude && this.longitude <= 180 
		&& this.latitude !== 'undefined' && -90 <= this.latitude && this.latitude <= 90;
	}
	
	static get typeString() {
    	return 'Out Of Range';
  	}
}