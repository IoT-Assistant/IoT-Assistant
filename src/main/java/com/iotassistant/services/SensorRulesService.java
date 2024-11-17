package com.iotassistant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.devices.transductors.SensorValues;
import com.iotassistant.models.rules.CameraSensorRule;
import com.iotassistant.models.rules.SensorRule;
import com.iotassistant.models.rules.SensorRuleType;
import com.iotassistant.models.rules.TriggerActuatorSensorRule;
import com.iotassistant.repositories.CameraSensorRuleJPARepository;
import com.iotassistant.repositories.SensorRuleJPARepository;
import com.iotassistant.repositories.TriggerActuatorSensorRuleJPARepository;

@Service
public class SensorRulesService {

	@Autowired
	private SensorRuleJPARepository sensorRuleJPARepository;
	
	@Autowired
	private CameraSensorRuleJPARepository cameraSensorRuleJPARepository;
	
	@Autowired
	private TriggerActuatorSensorRuleJPARepository triggerActuatorSensorRuleJPARepository;
	
	public void newSensorRule(SensorRule sensorRule) {
		sensorRuleJPARepository.saveAndFlush(sensorRule);	
	}
	

	public List<SensorRule> getAllSensorRules() {
		return sensorRuleJPARepository.findAll();
	}


	public boolean existSensorRule(SensorRule sensorRule) {
		for (SensorRule installedSensorRule : sensorRuleJPARepository.findAll()) {
			if (sensorRule.equals(installedSensorRule)) {
				return true;
			}
		}
		return false;
	}



	public SensorRule getSensorRule(int id) {
		Optional<SensorRule> sensorRule = sensorRuleJPARepository.findById(id);
		return sensorRule.isPresent() ? sensorRule.get() : null;
	}

	public void deleteSensorRuleById(int id)  {
		sensorRuleJPARepository.deleteById(Integer.valueOf(id));	
	}

	void deleteSensorRuleBySensorName(String sensorName) {
		List<SensorRule> allSensorRules = getAllSensorRules();
		for (SensorRule sensorRule : allSensorRules ) {
			if (sensorRule.getSensorName().equals(sensorName)) {
				deleteSensorRuleById(sensorRule.getId());
			}
		}		
	}

	void deleteTriggerActuatorSensorRules(String actuatorName)  {
		for (TriggerActuatorSensorRule triggerActuatorSensorRule :  triggerActuatorSensorRuleJPARepository.findAll() ) {
			if (triggerActuatorSensorRule.getActuatorName().equals(actuatorName)) {
				deleteSensorRuleById(triggerActuatorSensorRule.getId());
			}
		}		
	}
	
	void deleteCameraSensorRules(String cameraName)  {
		for (CameraSensorRule cameraSensorRule : cameraSensorRuleJPARepository.findAll() ) {
			if (cameraSensorRule.getCameraName().equals(cameraName)) {
				deleteSensorRuleById(cameraSensorRule.getId());
			}
		}		
	}
	
	public List<String> getSupportedSensorRulesTypes() {
		return SensorRuleType.getAllInstances();
	}

	void applyRules(String sensorName, SensorValues values) {
		 List<SensorRule> sensorRules = sensorRuleJPARepository.findAll();
		 for (SensorRule sensorRule : sensorRules) {
			 if (sensorRule.isEnabled() && sensorRule.apply(sensorName, values)) {
				 new TriggerSensorRuleService(sensorRule, values).trigger();
			 }
		 }
		
	}
}
