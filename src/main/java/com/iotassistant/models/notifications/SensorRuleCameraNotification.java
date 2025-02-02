package com.iotassistant.models.notifications;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.iotassistant.models.AnalogThresholdOperatorEnum;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.models.rules.CameraSensorRule;
import com.iotassistant.models.rules.RuleTriggerIntervalEnum;

@Entity
public class SensorRuleCameraNotification extends SensorRuleNotification {

	@NotNull
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private CameraSensorRule cameraSensorRule;

	@Lob
	@Column(columnDefinition="BLOB")
	private byte[] picture;
	

	public SensorRuleCameraNotification() {
	}

	public SensorRuleCameraNotification(CameraSensorRule cameraSensorRule, byte[] picture, String sensorValue, String date) {
		super(sensorValue, date);	
		this.cameraSensorRule = cameraSensorRule;
		this.picture = picture;
	}

	@Override
	public void accept(NotificationVisitor notificationVisitor) {
		notificationVisitor.visit(this);		
	}


	public CameraSensorRule getCameraSensorRule() {
		return cameraSensorRule;
	}


	@Override
	public Integer getSensorRuleId() {
		return cameraSensorRule.getId();
	}


	@Override
	public String getSensorName() {
		return cameraSensorRule.getSensorName();
	}


	@Override
	public PropertyMeasuredEnum getPropertyObserved() {
		return cameraSensorRule.getPropertyObserved();
	}


	@Override
	public AnalogThresholdOperatorEnum getValueThresholdOperator() {
		return cameraSensorRule.getAnalogThresholdOperator();
	}


	@Override
	public String getValueThresholdObserved() {
		return cameraSensorRule.getValueThresholdObserved();
	}


	@Override
	public RuleTriggerIntervalEnum getTimeBetweenTriggers() {
		return cameraSensorRule.getTimeBetweenTriggers();
	}

	public byte[] getPicture() {
		return picture;
	}


}
