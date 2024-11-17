package com.iotassistant.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.rules.GpsRule;
import com.iotassistant.models.rules.Rule;
import com.iotassistant.models.rules.RuleTriggerIntervalEnum;
import com.iotassistant.models.rules.SensorRule;
import com.iotassistant.repositories.RulesJPARepository;

@Service
public class RulesFacadeService {
	
	@Autowired
	private SensorRulesService sensorRulesService;
	
	@Autowired
	private GpsRulesService gpsRulesService;
	
	@Autowired
	private RulesJPARepository rulesJPARepository;
	
	private static RulesFacadeService instance;

	@PostConstruct
	private void registerInstance() {
		instance = this;
	} 
	
	public static RulesFacadeService getInstance() {
		return instance;
	}
	
	public List<String> getSupportedRulesTriggerInterval() {
		return RuleTriggerIntervalEnum.getAvailableTriggerIntervalOptions();
	}
	
	public void enableDisableRule(boolean enable, int id) {
		assert(getRule(id) != null);
		Rule rule = this.getRule(id);
		rule.setEnabled(enable);
		rulesJPARepository.saveAndFlush(rule);	
	}

	public Rule getRule(int id) {
		Optional<Rule> rule = rulesJPARepository.findById(id);
		return rule.isPresent() ? rule.get() : null;
	}

	public List<String> getSupportedSensorRulesTypes() {
		return sensorRulesService.getSupportedSensorRulesTypes();
	}

	public void newSensorRule(SensorRule sensorRule) {
		sensorRulesService.newSensorRule(sensorRule);
		
	}

	public boolean existEqualSensorRule(SensorRule sensorRule) {
		return sensorRulesService.existSensorRule(sensorRule);
	}

	public void deleteRuleById(int id) {
		rulesJPARepository.deleteById(id);
		
	}

	public List<Rule> getAllRules() {
		return rulesJPARepository.findAll();
	}

	public List<String> getSupportedGpsRulesTypes() {
		return gpsRulesService.getSupportedGpsRulesTypes();
	}

	public void newGpsRule(GpsRule gpsRule) {
		gpsRulesService.newGpsRule(gpsRule);
	}

}
