class GpsRules {
	
	constructor(outOfRangeGpsRules) {
		this.outOfRangeGpsRules = outOfRangeGpsRules;
	}
	
	getOutOfRangeGpsRules() {
		return this.outOfRangeGpsRules;
	}
	
	getAllGpsRules() {
		return this.getOutOfRangeGpsRules() ;
	}
	
}