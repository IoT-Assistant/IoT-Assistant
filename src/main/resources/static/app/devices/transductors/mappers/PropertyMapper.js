class PropertyMapper {
	
	constructor() {
	}
	
	static map(propertyObject) {
		return new Property(propertyObject.name, propertyObject.nameWithUnit, propertyObject.unit, propertyObject.binary, propertyObject.minimumValue, propertyObject.maximumValue);
	}
	
}