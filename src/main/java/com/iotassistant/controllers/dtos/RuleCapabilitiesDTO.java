package com.iotassistant.controllers.dtos;

import java.util.List;

public class RuleCapabilitiesDTO {
	
	private List<String> supportedSensorRulesTypes;
	
	private List<String> supportedGpsRulesTypes;
	
	private List<String> supportedRulesTimeBetweenTriggers;

	public RuleCapabilitiesDTO(List<String> supportedSensorRulesTypes, List<String> supportedGpsRulesTypes,
			List<String> supportedSensorRulesTimeBetweenTriggers) {
		super();
		this.supportedSensorRulesTypes = supportedSensorRulesTypes;
		this.supportedGpsRulesTypes = supportedGpsRulesTypes;
		this.supportedRulesTimeBetweenTriggers = supportedSensorRulesTimeBetweenTriggers;
	}

	public List<String> getSupportedSensorRulesTypes() {
		return supportedSensorRulesTypes;
	}

	public List<String> getSupportedGpsRulesTypes() {
		return supportedGpsRulesTypes;
	}

	public List<String> getSupportedRulesTimeBetweenTriggers() {
		return supportedRulesTimeBetweenTriggers;
	}

	
	
	

}
