class  OutOfRangeGpsRuleNotification extends Notification{
	
	
	constructor(id, outOfRangeGpsRule, date, latitude, longitude) {
		super(id, date);
		this.outOfRangeGpsRule = outOfRangeGpsRule;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	toString() {
		let gpsRule = this.outOfRangeGpsRule;
		let text = "Gps Out Of Range ";	
		text +=  "#" + gpsRule.getId() + " Notification: " + "Gps " + gpsRule.getGpsName() + " rule position is: latitude " 
		+ gpsRule.getLatitude() + " longitude  " + gpsRule.getLongitude() + " range  " + gpsRule.getRange() + ". Current position was: latitude " 
		+ this.latitude +  " longitude  " + this.longitude + ". Date: " + this.date;
		return text;
	}


	
}