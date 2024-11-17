package com.iotassistant.models.rules;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.iotassistant.models.SensorMeasureThresholdSettings;
import com.iotassistant.models.notifications.NotificationTypeEnum;
import com.iotassistant.services.SensorRulesService;


@Entity
@DiscriminatorValue("switchAlarmSensorRule")
public class EnableRuleSensorRule extends SensorRule {
	
	private int ruleId;
	
	private boolean enableAction;
	
	@Transient
	private SensorRulesService sensorRulesService;
	
	
	public EnableRuleSensorRule() {
		super();
	}

	public EnableRuleSensorRule(SensorMeasureThresholdSettings sensorMeasureThresholdSettings, boolean enabled, int ruleId, 
			RuleTriggerIntervalEnum timeBetweenTriggers, NotificationTypeEnum notificationType, boolean enableAction) {
		super(sensorMeasureThresholdSettings, enabled, timeBetweenTriggers, notificationType);
		this.ruleId = ruleId;
		this.enableAction = enableAction;
	}

	public int getRuleId() {
		return ruleId;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		EnableRuleSensorRule other = (EnableRuleSensorRule) obj;
		if (ruleId != other.ruleId)
			return false;
		if (!super.equals(obj))
			return false;	
		return true;
	}


	@Override
	public void accept(SensorRuleVisitor sensorRuleVisitor) {
		sensorRuleVisitor.visit(this);
		
	}

	

	public boolean isEnableAction() {
		return enableAction;
	}

	public SensorRulesService getSensorRulesService() {
		return sensorRulesService;
	}

}
