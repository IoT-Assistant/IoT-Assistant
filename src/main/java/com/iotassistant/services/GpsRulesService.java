package com.iotassistant.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.models.rules.GpsRule;
import com.iotassistant.models.rules.GpsRuleType;
import com.iotassistant.repositories.GpsRuleJPARepository;

@Service
public class GpsRulesService {
	
	@Autowired
	private GpsRuleJPARepository gpsRuleJPARepository;

	public List<String> getSupportedGpsRulesTypes() {
		return GpsRuleType.getAllInstances();
	}

	public void newGpsRule(GpsRule gpsRule) {
		gpsRuleJPARepository.save(gpsRule);
	}

	public void applyRules(String name, GpsPosition position) {
		List<GpsRule> gpsRules = gpsRuleJPARepository.findAll();
		 for (GpsRule gpsRule : gpsRules) {
			 if (gpsRule.isEnabled() && gpsRule.apply(name, position)) {
				 new TriggerGpsRuleService(gpsRule, position).trigger();
			 }
		 }
		
	}

	public void deleteGpsRules(String name) {
		for (GpsRule gpsRule : gpsRuleJPARepository.findAll() ) {
			if (gpsRule.getGpsName().equals(name)) {
				gpsRuleJPARepository.deleteById(Integer.valueOf(gpsRule.getId()));
			}
		}
		
	}
	
	
	
	

}
