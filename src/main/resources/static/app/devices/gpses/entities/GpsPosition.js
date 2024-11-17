class GpsPosition {
	
	constructor(longitude, latitude, time) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
	}
		
	getLongitude() {
		return this.longitude;
	}
	
	getLatitude() {
		return this.latitude;
	}
	
	getTime() {
		return this.time;
	}
	
	
}