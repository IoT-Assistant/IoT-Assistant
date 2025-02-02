package com.iotassistant.models.devices.transductors;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.iotassistant.models.devices.DeviceVisitor;
import com.iotassistant.models.devices.WatchdogInterval;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;

@Entity
@DiscriminatorValue("Actuator")
@Table(name="actuator")
@AttributeOverride(name="name", column = @Column(name="name", unique=true))
public class Actuator extends Transductor{
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private ActuatorValues values;
	
	private @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	ActuatorInterface actuatorInterface;
	
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@Enumerated(EnumType.STRING)
	private List<PropertyActuatedEnum> propertiesActuated;
	
	
	public Actuator() {
		super();
	}


	public Actuator(String name, String description, List<PropertyActuatedEnum> propertiesActuated, ActuatorInterface actuatorInterface, WatchdogInterval watchdogInterval) {
		super(name, description, watchdogInterval, actuatorInterface);
		this.actuatorInterface = actuatorInterface;
		this.propertiesActuated = propertiesActuated;
	}

	

	@Override
	public String getLastValueDate() {
		return ( values == null)? null: values.getDate();
	}


	public ActuatorValues getValues() {
		return this.values;	
	}


	public void setValues(ActuatorValues values) {
		this.values = values;
	}


	public List<PropertyActuatedEnum> getPropertiesActuated() {
		return this.propertiesActuated;
	}


	@Override
	public void accept(DeviceVisitor deviceVisitor) {
		deviceVisitor.visit(this);
		
	}


}
