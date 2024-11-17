package com.iotassistant.controllers.dtos;

import java.util.List;

import com.iotassistant.models.rules.GpsRule;
import com.iotassistant.models.rules.Rule;
import com.iotassistant.models.rules.RuleVisitor;
import com.iotassistant.models.rules.SensorRule;

public class RulesDTO implements RuleVisitor{
	
	SensorRulesDTO sensorRules = new SensorRulesDTO();
	
	GpsRulesDTO gpsRules = new GpsRulesDTO();

	public RulesDTO(List<Rule> allRules) {
		for (Rule rule: allRules) {
			rule.accept(this);
		}
	}

	@Override
	public void visit(SensorRule sensorRule) {
		sensorRules.add(sensorRule);	
	}

	@Override
	public void visit(GpsRule gpsRule) {
		gpsRules.add(gpsRule);	
	}

	public SensorRulesDTO getSensorRules() {
		return sensorRules;
	}

	public GpsRulesDTO getGpsRules() {
		return gpsRules;
	}
	
	

}
