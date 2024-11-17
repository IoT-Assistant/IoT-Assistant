package com.iotassistant.models.devices.transductors;


public interface Property {
	
	public static final String BINARY_TRUE_STRING = "true";
	
	public static final String BINARY_FALSE_STRING = "false";
	
	String getName();
	
	String getNameWithUnit();
	
	boolean isBinary();
	
	String getUnit();

	Integer getMaximumValue();

	Integer getMinimumValue();

	String getDescriptionFromValue(String value);

	public default boolean isValidValue(String string) {
		if (this.isBinary()) {
			return string != null && string.equalsIgnoreCase(BINARY_TRUE_STRING) 
					|| string.equalsIgnoreCase(BINARY_FALSE_STRING);
		} else {
			try {
				float number = Float.parseFloat(string);
				return this.getMinimumValue() <= number && number <= this.getMaximumValue();
			} catch(  NullPointerException | NumberFormatException e) {
				return false;
			}		
		}
	}
	
	public static boolean getBooleanFromString(String string) {
		assert(BINARY_TRUE_STRING.equals(string) || BINARY_FALSE_STRING.equals(string));
		return BINARY_TRUE_STRING.equals(string);
	}
	
	public static String getStringFromBoolean(boolean value) {
		return value ? BINARY_TRUE_STRING : BINARY_FALSE_STRING;
	}

}
