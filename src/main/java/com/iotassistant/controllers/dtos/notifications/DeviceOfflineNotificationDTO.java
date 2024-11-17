package com.iotassistant.controllers.dtos.notifications;

import com.iotassistant.models.notifications.DeviceOfflineNotification;

class DeviceOfflineNotificationDTO extends NotificationDTO{
	
	private String deviceName;
	
	private String watchdogInterval;

	DeviceOfflineNotificationDTO(DeviceOfflineNotification offlineNotification) {
		super(offlineNotification.getId(), offlineNotification.getDate());
		this.deviceName =  offlineNotification.getDeviceName();
		this.watchdogInterval = offlineNotification.getWatchdogInterval();
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getWatchdogInterval() {
		return watchdogInterval;
	}


	

}
