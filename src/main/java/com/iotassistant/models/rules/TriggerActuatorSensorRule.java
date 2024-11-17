package com.iotassistant.models.rules;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.iotassistant.models.SensorMeasureThresholdSettings;
import com.iotassistant.models.devices.transductors.Property;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;
import com.iotassistant.models.notifications.NotificationTypeEnum;

@Entity
@DiscriminatorValue("triggerActuatorSensorRule")
public class TriggerActuatorSensorRule extends SensorRule{
	
	private String actuatorName;
	
	@Enumerated(EnumType.STRING)
	private PropertyActuatedEnum propertyActuated;
	
	private String actuatorSetValue;
	

	public TriggerActuatorSensorRule() {
		super();
	}

	public TriggerActuatorSensorRule(SensorMeasureThresholdSettings sensorMeasureThresholdSettings, boolean enabled, RuleTriggerIntervalEnum timeBetweenTriggers,
			String actuatorName, String actuatorSetValue, PropertyActuatedEnum propertyActuated, NotificationTypeEnum notificationType) {
		super(sensorMeasureThresholdSettings, enabled, timeBetweenTriggers, notificationType);
		this.actuatorName = actuatorName;
		this.actuatorSetValue = actuatorSetValue;
		this.propertyActuated = propertyActuated;
	}

	public String getActuatorName() {
		return actuatorName;
	}


	public PropertyActuatedEnum getPropertyActuated() {
		return propertyActuated;
	}


	public String getActuatorSetValue() {
		return actuatorSetValue;
	}
	
	@Override
	public void accept(SensorRuleVisitor sensorRuleVisitor) {
		sensorRuleVisitor.visit(this);
	}
	
	public String getActuactorNewValue(String currentActuatorValue) {
		if (propertyActuated.isBinary() && actuatorSetValue.equals("switch")) {
			return Property.getStringFromBoolean(!Property.getBooleanFromString(currentActuatorValue));
		}
		return this.getActuatorSetValue();
	}

	
	public boolean isValidValue() {
		return propertyActuated.isValidValue(actuatorSetValue) || (propertyActuated.isBinary() && actuatorSetValue.equals("switch"));
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		TriggerActuatorSensorRule other = (TriggerActuatorSensorRule) obj;
		if (actuatorName != other.getActuatorName())
			return false;
		if (!super.equals(obj))
			return false;	
		return true;
	}

}
