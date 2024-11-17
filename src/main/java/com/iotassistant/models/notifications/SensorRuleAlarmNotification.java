package com.iotassistant.models.notifications;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.iotassistant.models.AnalogThresholdOperatorEnum;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.models.rules.AlarmSensorRule;
import com.iotassistant.models.rules.RuleTriggerIntervalEnum;


@Entity
public class SensorRuleAlarmNotification extends SensorRuleNotification {
	
	@NotNull
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private AlarmSensorRule alarmSensorRule;
	
	public SensorRuleAlarmNotification() {
		super();
	}


	public SensorRuleAlarmNotification(AlarmSensorRule alarmSensorRule, String sensorValue, String date) {
		super(sensorValue, date);	
		this.alarmSensorRule = alarmSensorRule;
	}


	@Override
	public void accept(NotificationVisitor notificationVisitor) {
		notificationVisitor.visit(this);		
	}


	public AlarmSensorRule getAlarmSensorRule() {
		return alarmSensorRule;
	}


	@Override
	public Integer getSensorRuleId() {
		return alarmSensorRule.getId();
	}


	@Override
	public String getSensorName() {
		return alarmSensorRule.getSensorName();
	}


	@Override
	public PropertyMeasuredEnum getPropertyObserved() {
		return alarmSensorRule.getPropertyObserved();
	}


	@Override
	public AnalogThresholdOperatorEnum getValueThresholdOperator() {
		return alarmSensorRule.getAnalogThresholdOperator();
	}


	@Override
	public String getValueThresholdObserved() {
		return alarmSensorRule.getValueThresholdObserved();
	}


	@Override
	public RuleTriggerIntervalEnum getTimeBetweenTriggers() {
		return alarmSensorRule.getTimeBetweenTriggers();
	}








}
