package com.iotassistant.controllers.dtos.notifications;

import com.iotassistant.models.notifications.OutOfRangeGpsRuleNotification;

public class GpsRuleNotificationDTO extends NotificationDTO{
	

	public GpsRuleNotificationDTO(OutOfRangeGpsRuleNotification outOfRangeGpsRuleNotification) {
		super(outOfRangeGpsRuleNotification.getId(), outOfRangeGpsRuleNotification.getDate());
	}

}
