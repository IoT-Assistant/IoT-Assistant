package com.iotassistant.models.devices;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="device_type")
@Table(name="device")
public abstract class Device {
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
	
	@Pattern(regexp = "^[A-Za-z0-9 ]{0,30}$")
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private DeviceInterface deviceInterface;

	private String description;
	
	private boolean active;
	
	@Enumerated(EnumType.STRING)
	private WatchdogInterval watchdogInterval;
	
	private boolean watchdogEnabled;

	
	public Device() {
		super();
	}
	
	public Device(String name, String description, WatchdogInterval watchdogInterval, DeviceInterface deviceInterface) {
		this();
		this.name = name;
		this.description = description;
		this.watchdogInterval = watchdogInterval;
		this.active = false;
		this.deviceInterface = deviceInterface;
		if (!watchdogInterval.equals(WatchdogInterval.NO_WATCHDOG)) {
			this.watchdogEnabled = true;
		}
	}
	
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public DeviceInterface getInterface() {
		return this.deviceInterface;
	}
	
	public WatchdogInterval getWatchdogInterval() {
		return watchdogInterval;
	}
	
	public boolean hasWatchdog() {
		return watchdogInterval != WatchdogInterval.NO_WATCHDOG;
	}
	
	public boolean isWatchdogEnabled() {
		return watchdogEnabled;
	}

	public void setWatchdogEnabled(boolean watchdogEnabled) {
		this.watchdogEnabled = watchdogEnabled;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	

	public abstract void accept(DeviceVisitor deviceVisitor);
	
}
