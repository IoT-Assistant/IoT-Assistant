class TransductorValues {
	
	constructor(time, values) {
		this.time = time;
		this.values = values;
	}
	
	
	
	getTime() {
		return this.time;
	}
	
	
	getValue(property) {
		return this.values[property];
	}
	
	
}