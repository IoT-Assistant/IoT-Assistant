package com.iotassistant.controllers.dtos;

import com.iotassistant.models.rules.OutOfRangeGpsRule;

public class GpsRuleDTO extends RuleDTO{
	
	private String gpsName;

	public String getGpsName() {
		return gpsName;
	}

	public GpsRuleDTO(OutOfRangeGpsRule rule) {
		super(rule);
		this.gpsName = rule.getGpsName();
	}

	public GpsRuleDTO() {
	}
	
	
	
	

}
