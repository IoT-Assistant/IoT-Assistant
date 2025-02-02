

class Sensor extends Transductor{
	
	constructor(name, description, active, values, propertiesMeasured, watchdogInterval, watchdogEnabled) {
		super(name, description, active, propertiesMeasured, watchdogInterval, watchdogEnabled);
		this.values = values;
	}
	
	
	getValuesDate() {
		return this.values.getDate();
	}
	
	getValuesTime() {
		return this.values.getTime();
	}
	
	getValue(propertyMeasured) {
		return this.values.getValue(propertyMeasured);
	}
	
	
	
}

