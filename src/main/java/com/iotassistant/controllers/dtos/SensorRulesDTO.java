package com.iotassistant.controllers.dtos;

import java.util.ArrayList;

import com.iotassistant.models.rules.AlarmSensorRule;
import com.iotassistant.models.rules.CameraSensorRule;
import com.iotassistant.models.rules.EnableRuleSensorRule;
import com.iotassistant.models.rules.SensorRule;
import com.iotassistant.models.rules.SensorRuleVisitor;
import com.iotassistant.models.rules.TriggerActuatorSensorRule;

public class SensorRulesDTO implements SensorRuleVisitor{
	
	private ArrayList<AlarmSensorRuleDTO> alarmSensorRules = new ArrayList<AlarmSensorRuleDTO>();
	
	private ArrayList<EnableRuleSensorRuleDTO> enableSensorRules = new ArrayList<EnableRuleSensorRuleDTO>();

	private ArrayList<TriggerActuatorSensorRuleDTO> triggerActuatorSensorRules = new ArrayList<TriggerActuatorSensorRuleDTO>();

	private ArrayList<CameraSensorRuleDTO> cameraSensorRules = new ArrayList<CameraSensorRuleDTO>();

	
	public SensorRulesDTO() {
		super();
	}

	public void add(SensorRule sensorRule) {
		sensorRule.accept(this);
	}

	public ArrayList<AlarmSensorRuleDTO> getAlarmSensorRules() {
		return alarmSensorRules;
	}

	public ArrayList<EnableRuleSensorRuleDTO> getEnableSensorRules() {
		return enableSensorRules;
	}

	public ArrayList<TriggerActuatorSensorRuleDTO> getTriggerActuatorSensorRules() {
		return triggerActuatorSensorRules;
	}

	public ArrayList<CameraSensorRuleDTO> getCameraSensorRules() {
		return cameraSensorRules;
	}

	@Override
	public void visit(EnableRuleSensorRule enableRuleSensorRule) {
		this.enableSensorRules.add(new EnableRuleSensorRuleDTO(enableRuleSensorRule));
	}

	@Override
	public void visit(TriggerActuatorSensorRule triggerActuatorSensorRule) {
		this.triggerActuatorSensorRules.add(new TriggerActuatorSensorRuleDTO(triggerActuatorSensorRule));
		
	}

	@Override
	public void visit(AlarmSensorRule alarmSensorRule) {
		this.alarmSensorRules.add(new AlarmSensorRuleDTO(alarmSensorRule));		
	}

	@Override
	public void visit(CameraSensorRule cameraSensorRule) {
		this.cameraSensorRules.add(new CameraSensorRuleDTO(cameraSensorRule));	
		
	}
}
