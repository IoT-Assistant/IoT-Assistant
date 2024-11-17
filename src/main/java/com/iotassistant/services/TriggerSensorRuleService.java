package com.iotassistant.services;

import java.text.ParseException;

import com.iotassistant.models.devices.CameraInterfaceException;
import com.iotassistant.models.devices.transductors.SensorValues;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;
import com.iotassistant.models.notifications.Notification;
import com.iotassistant.models.notifications.SensorRuleAlarmNotification;
import com.iotassistant.models.notifications.SensorRuleCameraNotification;
import com.iotassistant.models.notifications.SensorRuleEnableRuleNotification;
import com.iotassistant.models.notifications.SensorRuleNotification;
import com.iotassistant.models.notifications.SensorRuleTriggerActuatorNotification;
import com.iotassistant.models.rules.AlarmSensorRule;
import com.iotassistant.models.rules.CameraSensorRule;
import com.iotassistant.models.rules.EnableRuleSensorRule;
import com.iotassistant.models.rules.SensorRule;
import com.iotassistant.models.rules.SensorRuleVisitor;
import com.iotassistant.models.rules.TriggerActuatorSensorRule;
import com.iotassistant.utils.Date;

public class TriggerSensorRuleService implements SensorRuleVisitor{
	
	private SensorRule sensorRule;
	
	private SensorValues values;

	public TriggerSensorRuleService(SensorRule sensorRule, SensorValues values) {
		super();
		this.sensorRule = sensorRule;
		this.values = values;
	}

	public void trigger() {
		if (this.isTriggerIntervalReached()) {
			sensorRule.accept(this);
		}
	}

	private boolean isTriggerIntervalReached() {
		try {
			SensorRuleNotification lastNotification = getLastNotification(this.sensorRule.getId());
			return (lastNotification == null) ? true : Date.havePassedMinutesBetweenDates(lastNotification.getDate(), values.getDate(), sensorRule.getTimeBetweenTriggers().getMinutes());
		} catch (ParseException e) {
			return false;
		}
		
	}
	
	private SensorRuleNotification getLastNotification(Integer sensorRuleId) {
		for(SensorRuleNotification sensorRuleNotification: NotificationsService.getInstance().getSensorRulesNotificationsByIdDesc()) {
			if (sensorRuleNotification.getSensorRuleId().equals(sensorRuleId)) {
				return sensorRuleNotification;
			}
		}
		return null;
	}
	

	@Override
	public void visit(EnableRuleSensorRule enableRuleSensorRule) {
		SensorRuleEnableRuleNotification sensorRuleEnableRuleNotification = new SensorRuleEnableRuleNotification(enableRuleSensorRule, values.getValue(sensorRule.getPropertyObserved()), values.getDate());			
		this.sendNotification( enableRuleSensorRule, sensorRuleEnableRuleNotification);	
		RulesFacadeService.getInstance().enableDisableRule(enableRuleSensorRule.isEnableAction(), enableRuleSensorRule.getRuleId());
			
	}
	
	public void sendNotification(SensorRule sensorRule, Notification notification) {
		NotificationsService.getInstance().sendNotification(sensorRule.getNotificationType(), notification);
	}

	@Override
	public void visit(TriggerActuatorSensorRule triggerActuatorSensorRule) {
		SensorRuleTriggerActuatorNotification sensorRuleTriggerActuatorNotification = new SensorRuleTriggerActuatorNotification(triggerActuatorSensorRule, values.getValue(sensorRule.getPropertyObserved()), values.getDate());			
		this.sendNotification( triggerActuatorSensorRule, sensorRuleTriggerActuatorNotification);
		PropertyActuatedEnum propertyActuated = triggerActuatorSensorRule.getPropertyActuated();
		String currentActuatorValue = ActuatorsService.getInstance().getByName(triggerActuatorSensorRule.getActuatorName()).getValues().getValue(propertyActuated);
		ActuatorsService.getInstance().setActuatorValue(propertyActuated, triggerActuatorSensorRule.getActuatorName(), triggerActuatorSensorRule.getActuactorNewValue(currentActuatorValue));	
	}

	@Override
	public void visit(AlarmSensorRule alarmSensorRule) {
		SensorRuleAlarmNotification sensorRuleAlarmNotification = new SensorRuleAlarmNotification(alarmSensorRule, values.getValue(sensorRule.getPropertyObserved()), values.getDate());
		this.sendNotification( alarmSensorRule, sensorRuleAlarmNotification);	
	}

	@Override
	public void visit(CameraSensorRule cameraSensorRule) {
		byte[] picture = null;
		try {
			picture = CamerasService.getInstance().getPicture(cameraSensorRule.getCameraName());		
		} catch (CameraInterfaceException e) {
		} finally	{
			SensorRuleCameraNotification SensorRuleAlarmNotification = new SensorRuleCameraNotification(cameraSensorRule, picture, values.getValue(sensorRule.getPropertyObserved()), values.getDate());
			this.sendNotification( cameraSensorRule, SensorRuleAlarmNotification);
		}
		
	}

	
}
