package com.iotassistant.models.devices.transductors;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.CascadeType;
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
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;

@Entity
@DiscriminatorValue("Sensor")
@Table(name="sensor")
@AttributeOverride(name="name", column = @Column(name="name", unique=true))
public class Sensor extends Transductor {
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private SensorValues values;

	
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@Enumerated(EnumType.STRING)
	private List<PropertyMeasuredEnum> propertiesMeasured;

	public Sensor() {
		super();
	}

	public Sensor(String name, String description, List<PropertyMeasuredEnum> properties, SensorInterface sensorInterface, WatchdogInterval watchdogInterval) {
		super(name, description, watchdogInterval, sensorInterface);
		this.propertiesMeasured = properties;
		this.values = null;
	}

	public SensorValues getValues() {
		return values;
	}

	
	public void setValues(SensorValues values) {
		this.values = values;
	}

	public List<PropertyMeasuredEnum> getPropertiesMeasured() {
		return propertiesMeasured;
	}
	

	@Override
	public String getLastValueDate() {
		return ( values == null)? null: values.getDate();
	}

	public String getValue(PropertyMeasuredEnum propertyMeasured) {
		return values.getValue(propertyMeasured);
	}

	@Override
	public void accept(DeviceVisitor deviceVisitor) {
		deviceVisitor.visit(this);
		
	}
	





}
