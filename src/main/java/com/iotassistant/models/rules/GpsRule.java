package com.iotassistant.models.rules;

import javax.persistence.Entity;

import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.models.notifications.NotificationTypeEnum;

@Entity
public abstract class GpsRule extends Rule {
	
	private String gpsName;
	
	
	@Override
	public void accept(RuleVisitor ruleVisitor) {
		ruleVisitor.visit(this);	
	}


	public GpsRule() {
		super();
	}

	public GpsRule(String gpsName, boolean enabled, RuleTriggerIntervalEnum timeBetweenTriggers,
			NotificationTypeEnum notificationType) {
		super(enabled, timeBetweenTriggers, notificationType);
		this.gpsName = gpsName;
	}
	
	public abstract boolean apply(String gpsName, GpsPosition position);
	


	public String getGpsName() {
		return gpsName;
	}


	public abstract void accept(GpsRuleVisitor gpsRuleVisitor);

}
