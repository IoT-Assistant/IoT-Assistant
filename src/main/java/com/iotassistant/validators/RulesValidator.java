package com.iotassistant.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.models.rules.AlarmSensorRule;
import com.iotassistant.models.rules.CameraSensorRule;
import com.iotassistant.models.rules.EnableRuleSensorRule;
import com.iotassistant.models.rules.GpsRule;
import com.iotassistant.models.rules.OutOfRangeGpsRule;
import com.iotassistant.models.rules.SensorRule;
import com.iotassistant.models.rules.TriggerActuatorSensorRule;
import com.iotassistant.services.DevicesFacadeService;
import com.iotassistant.services.RulesFacadeService;

@Component
public class RulesValidator {
	
	@Autowired
	private RulesFacadeService rulesService;
	
	@Autowired
	DevicesFacadeService devicesFacadeService;
	
	public ValidationError validateNewEnableRuleSensorRule(EnableRuleSensorRule rule) {
		ValidationError error = this.validateExist(rule.getRuleId());
		if (!error.hasErrors()) {
			error = this.validateNewRule(rule);
		}
		return error;
	}	
	
	public ValidationError validateExist(int id) {
		ValidationError error = ValidationError.NO_ERROR;
		if (rulesService.getRule(id) == null) {
			error = ValidationError.ENTITY_NOT_FOUND;
			error.formatMsg("Rule " + id);
        }	
		return error;
	}

	private ValidationError validateNewRule(SensorRule sensorRule) {
		ValidationError error = ValidationError.NO_ERROR;
		if (!devicesFacadeService.existSensor(sensorRule.getSensorName()))  {
			error = ValidationError.ENTITY_NOT_FOUND;
			error.formatMsg("Sensor" + sensorRule.getSensorName());
	    }
		else if (rulesService.existEqualSensorRule(sensorRule)) {
			error = ValidationError.ENTITY_ALREADY_EXIST;
			error.formatMsg("Rule");
        }
		else if (!devicesFacadeService.sensorHasProperty(sensorRule.getSensorName(), sensorRule.getPropertyObserved()))  {
			error = ValidationError.ENTITY_HAS_NOT_PROPERTY;
			error.formatMsg("Sensor");
	    }
		else if(!sensorRule.getPropertyObserved().isValidValue(sensorRule.getValueThresholdObserved())) {
			error = ValidationError.ENTITY_VALUE_IS_NOT_VALID;
			error.formatMsg(sensorRule.getValueThresholdObserved());
		}
		return error;
	}

	public ValidationError validateNewAlarmSensorRule(AlarmSensorRule rule) {
		return this.validateNewRule(rule);
	}
	
	public ValidationError validateNewOutOfRangeGpsRule(OutOfRangeGpsRule rule) {
		ValidationError error = ValidationError.NO_ERROR;
		if (!GpsPosition.isValidPosition(rule.getLongitude(), rule.getLatitude())) {
			error = ValidationError.INVALID_GPS_POSITION;
	    } else {
	    	error = this.validateNewRule(rule);
	    }
		return error;
	}
	
	private ValidationError validateNewRule(GpsRule gpsRule) {
		ValidationError error = ValidationError.NO_ERROR;
		if (!devicesFacadeService.existGps(gpsRule.getGpsName()))  {
			error = ValidationError.ENTITY_NOT_FOUND;
			error.formatMsg("Gps");
	    }
		return error;
	}


	public ValidationError validateEnable(int id) {
		return this.validateExist(id);
	}
	
	public ValidationError validateDelete(int id) {
		return this.validateExist(id);
	}
	
	public ValidationError validateNewTriggerActuatorSensorRule(TriggerActuatorSensorRule rule) {
		ValidationError error = ValidationError.NO_ERROR;
		if (!devicesFacadeService.existActuator(rule.getActuatorName())) {
			error = ValidationError.ENTITY_NOT_FOUND;
			error.formatMsg("Actuator" + rule.getActuatorName());
		}
		else if (!rule.isValidValue()) {
			error = ValidationError.ENTITY_VALUE_IS_NOT_VALID;
			error.formatMsg(rule.getActuatorSetValue());
		}
		if (!error.hasErrors()) {
			error = this.validateNewRule(rule);
		}
		return error;
	}	
	
	public ValidationError validateNewCameraSensorRule(CameraSensorRule rule) {
		ValidationError error = ValidationError.NO_ERROR;
		if (!devicesFacadeService.existCamera(rule.getCameraName())) {
			error = ValidationError.ENTITY_NOT_FOUND;
			error.formatMsg("Camera" + rule.getCameraName());
		}
		if (!error.hasErrors()) {
			error = this.validateNewRule(rule);
		}
		return error;
	}

}