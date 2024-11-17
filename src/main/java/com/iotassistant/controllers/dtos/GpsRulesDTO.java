package com.iotassistant.controllers.dtos;

import java.util.ArrayList;

import com.iotassistant.models.rules.GpsRule;
import com.iotassistant.models.rules.GpsRuleVisitor;
import com.iotassistant.models.rules.OutOfRangeGpsRule;

public class GpsRulesDTO implements GpsRuleVisitor{
	
	private ArrayList<OutOfRangeGpsRuleDTO> outOfRangeGpsRules = new ArrayList<OutOfRangeGpsRuleDTO>();

	public void add(GpsRule gpsRule) {
		gpsRule.accept(this);
		
	}

	@Override
	public void visit(OutOfRangeGpsRule outOfRangeGpsRule) {
		outOfRangeGpsRules.add(new OutOfRangeGpsRuleDTO(outOfRangeGpsRule));
	}

	public ArrayList<OutOfRangeGpsRuleDTO> getOutOfRangeGpsRules() {
		return outOfRangeGpsRules;
	}
	
	

}
