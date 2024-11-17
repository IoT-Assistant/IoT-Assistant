package com.iotassistant.models.notifications;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.iotassistant.models.devices.Device;
import com.iotassistant.models.devices.WatchdogInterval;

@Entity
public class DeviceOfflineNotification extends Notification{
	
	@NotNull
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Device device;
	
	private String watchdogInterval;

	public DeviceOfflineNotification() {
		super();
	}

	public DeviceOfflineNotification(Device device, String date, WatchdogInterval watchdogInterval) {
		super(date);
		this.device = device;
		this.watchdogInterval = watchdogInterval.toString();
	}

	public String getDeviceName() {
		return device.getName();
	}
	
	@Override
	public void accept(NotificationVisitor notificationVisitor) {
		notificationVisitor.visit(this);		
	}

	public String getWatchdogInterval() {
		return watchdogInterval;
	}
	
	

}
