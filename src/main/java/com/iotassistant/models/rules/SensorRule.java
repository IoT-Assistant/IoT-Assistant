package com.iotassistant.models.rules;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.iotassistant.models.AnalogThresholdOperatorEnum;
import com.iotassistant.models.SensorMeasureThresholdSettings;
import com.iotassistant.models.devices.transductors.SensorValues;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.models.notifications.NotificationTypeEnum;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="sensorrule_type")
@Table(name="sensorRule")
public abstract class SensorRule extends Rule{
	
	@OneToOne(orphanRemoval = true, cascade=CascadeType.ALL)
	private SensorMeasureThresholdSettings sensorMeasureThresholdSettings;

	public SensorRule() {
		super();
	}

	SensorRule(SensorMeasureThresholdSettings sensorMeasureThresholdSettings, boolean enabled, 
			RuleTriggerIntervalEnum timeBetweenTriggers, NotificationTypeEnum notificationType) {
		super(enabled, timeBetweenTriggers, notificationType);
		this.sensorMeasureThresholdSettings = sensorMeasureThresholdSettings;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSensorName() {
		return sensorMeasureThresholdSettings.getSensorName();
	}

	public PropertyMeasuredEnum getPropertyObserved() {
		return sensorMeasureThresholdSettings.getPropertyObserved();
	}


	public String getValueThresholdObserved() {
		return sensorMeasureThresholdSettings.getValueThresholdObserved();
	}

	public SensorMeasureThresholdSettings getSensorMeasureThresholdSettings() {
		return sensorMeasureThresholdSettings;
	}


	public AnalogThresholdOperatorEnum getAnalogThresholdOperator() {
		return sensorMeasureThresholdSettings.getAnalogThresholdOperator();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorRule other = (SensorRule) obj;
		if (!sensorMeasureThresholdSettings.equals(other.sensorMeasureThresholdSettings))
			return false;
		return true;
	}
	
	public abstract void accept(SensorRuleVisitor sensorRuleVisitor);

	public boolean apply(String sensorName, SensorValues values) {
		return sensorMeasureThresholdSettings.apply(sensorName, values);
		
	}
	
	@Override
	public void accept(RuleVisitor ruleVisitor) {
		ruleVisitor.visit(this);
	}

	

}
