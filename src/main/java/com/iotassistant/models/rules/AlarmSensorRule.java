package com.iotassistant.models.rules;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iotassistant.models.SensorMeasureThresholdSettings;
import com.iotassistant.models.notifications.NotificationTypeEnum;

@Entity
@DiscriminatorValue("alarmSensorRule")
public class AlarmSensorRule extends SensorRule{


	public AlarmSensorRule() {
		super();
	}
	
	public AlarmSensorRule(SensorMeasureThresholdSettings sensorMeasureThresholdSettings, NotificationTypeEnum notificationType, 
			RuleTriggerIntervalEnum timeBetweenTriggers,  boolean enabled) {
		super(sensorMeasureThresholdSettings, enabled, timeBetweenTriggers, notificationType);
	}
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	

	@Override
	public void accept(SensorRuleVisitor sensorRuleVisitor) {
		sensorRuleVisitor.visit(this);		
	}
	


}
