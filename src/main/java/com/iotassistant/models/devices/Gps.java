package com.iotassistant.models.devices;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Gps")
@Table(name="gps")
@AttributeOverride(name="name", column = @Column(name="name", unique=true))
public class Gps extends Device{

	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private GpsPosition position;

	public Gps() {
		super();
	}

	public Gps(String name, String description, GpsInterface gpsInterface, WatchdogInterval watchdogInterval) {
		super(name, description, watchdogInterval, gpsInterface);
	}

	@Override
	public void accept(DeviceVisitor deviceVisitor) {
		deviceVisitor.visit(this);
		
	}

	
	public String getLastValueDate() {
		return this.position.getDate();
	}
	

	public GpsPosition getPosition() {
		return position;
	}

	public void setPosition(GpsPosition position) {
		this.position = position;
		
	}

}
