package com.iotassistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iotassistant.controllers.dtos.AlarmSensorRuleDTO;
import com.iotassistant.controllers.dtos.CameraSensorRuleDTO;
import com.iotassistant.controllers.dtos.EnableRuleSensorRuleDTO;
import com.iotassistant.controllers.dtos.ErrorDTO;
import com.iotassistant.controllers.dtos.OutOfRangeGpsRuleDTO;
import com.iotassistant.controllers.dtos.RulesDTO;
import com.iotassistant.controllers.dtos.TriggerActuatorSensorRuleDTO;
import com.iotassistant.controllers.dtos.devices.EnableDTO;
import com.iotassistant.models.rules.AlarmSensorRule;
import com.iotassistant.models.rules.CameraSensorRule;
import com.iotassistant.models.rules.EnableRuleSensorRule;
import com.iotassistant.models.rules.GpsRule;
import com.iotassistant.models.rules.OutOfRangeGpsRule;
import com.iotassistant.models.rules.SensorRule;
import com.iotassistant.models.rules.TriggerActuatorSensorRule;
import com.iotassistant.services.RulesFacadeService;
import com.iotassistant.validators.RulesValidator;
import com.iotassistant.validators.ValidationError;

@RestController
@RequestMapping("${rules.uri}")
public class RulesController {
	
	@Autowired
	private RulesFacadeService rulesService;
	
	@Autowired
	RulesValidator rulesValidator;
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllSensorRules() {
		RulesDTO rulesDTO = new RulesDTO(rulesService.getAllRules());
		return new ResponseEntity<>(rulesDTO, HttpStatus.CREATED);					
	}
	
	@RequestMapping(value="/enableRuleSensorRules/", method = RequestMethod.POST)
	public ResponseEntity<?> newEnableRuleSensorRule(@RequestBody EnableRuleSensorRuleDTO enableRuleSensorRuleDTO) {
		EnableRuleSensorRule enableRuleSensorRule = enableRuleSensorRuleDTO.getSensorRule();
		ValidationError validationError = rulesValidator.validateNewEnableRuleSensorRule(enableRuleSensorRule);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
		return this.newSensorRule(enableRuleSensorRule);
	}
	
	private ResponseEntity<?> newSensorRule(SensorRule sensorRule)  {
		rulesService.newSensorRule(sensorRule);
	    return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@RequestMapping(value="/alarmSensorRules/", method = RequestMethod.POST)
	public ResponseEntity<?> newAlarmSensorRule(@RequestBody AlarmSensorRuleDTO alarmSensorRuleDTO)   {
	    AlarmSensorRule alarmSensorRule = alarmSensorRuleDTO.getSensorRule();
	    ValidationError validationError = rulesValidator.validateNewAlarmSensorRule(alarmSensorRule);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    return this.newSensorRule(alarmSensorRule);
	}
	
	@RequestMapping(value="/outOfRangeGpsRules/", method = RequestMethod.POST)
	public ResponseEntity<?> newOutOfRangeGpsRule(@RequestBody OutOfRangeGpsRuleDTO outOfRangeGpsRuleDTO)   {
	    OutOfRangeGpsRule outOfRangeGpsRule = outOfRangeGpsRuleDTO.getOutOfRangeGpsRule();
	    ValidationError validationError = rulesValidator.validateNewOutOfRangeGpsRule(outOfRangeGpsRule);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    return this.newGpsRule(outOfRangeGpsRule);
	}
	
	private ResponseEntity<?> newGpsRule(GpsRule gpsRule) {
		rulesService.newGpsRule(gpsRule);
	    return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> enableRuleById(@PathVariable("id") int id, @RequestBody EnableDTO enableRuleDTO)   {
		ValidationError validationError = rulesValidator.validateEnable(id);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}	
		rulesService.enableDisableRule(enableRuleDTO.isEnable(), id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRuleById(@PathVariable("id") int id)   {
		ValidationError validationError = rulesValidator.validateDelete(id);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}	
		rulesService.deleteRuleById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value="/triggerActuatorSensorRules/", method = RequestMethod.POST)
	public ResponseEntity<?> newTriggerActuatorSensorRule(@RequestBody TriggerActuatorSensorRuleDTO triggerActuatorSensorRuleDTO)     {
		TriggerActuatorSensorRule triggerActuatorrRule = triggerActuatorSensorRuleDTO.getSensorRule();
		ValidationError validationError = rulesValidator.validateNewTriggerActuatorSensorRule(triggerActuatorrRule);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}	
		return this.newSensorRule(triggerActuatorrRule);	
	}
	
	
	@RequestMapping(value="/cameraSensorRules/", method = RequestMethod.POST)
	public ResponseEntity<?> newCameraSensorRule(@RequestBody CameraSensorRuleDTO cameraSensorRuleDTO) {
		CameraSensorRule cameraSensorRule = cameraSensorRuleDTO.getSensorRule();
		ValidationError validationError = rulesValidator.validateNewCameraSensorRule(cameraSensorRule);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}	
		return this.newSensorRule(cameraSensorRule);	
	}

}
