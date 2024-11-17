package com.iotassistant.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotassistant.models.SensorMeasureThresholdSettings;
import com.iotassistant.models.notifications.NotificationTypeEnum;
import com.iotassistant.models.rules.AlarmSensorRule;
import com.iotassistant.models.rules.RuleTriggerIntervalEnum;

public class AlarmSensorRuleDTO extends SensorRuleDTO{

	public AlarmSensorRuleDTO() {
		super();
	}

	public AlarmSensorRuleDTO(AlarmSensorRule alarmSensorRule) {
		super(alarmSensorRule);
	}

	@JsonIgnore
	public AlarmSensorRule getSensorRule() {
			RuleTriggerIntervalEnum timeBetweenTriggers = RuleTriggerIntervalEnum.getInstance(this.getTimeBetweenTriggers());
			NotificationTypeEnum notificationType = NotificationTypeEnum.getInstance(this.getNotificationType());
			SensorMeasureThresholdSettings sensorMeasureThresholdSettings = this.sensorMeasureThresholdSettings.getSensorMeasureThresholdSettings();
			return new AlarmSensorRule(sensorMeasureThresholdSettings, notificationType, timeBetweenTriggers, this.isEnabled());
	}


	
	

}
