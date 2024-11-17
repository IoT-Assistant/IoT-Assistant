package com.iotassistant.controllers.dtos.notifications;

import com.iotassistant.controllers.dtos.OutOfRangeGpsRuleDTO;
import com.iotassistant.models.notifications.OutOfRangeGpsRuleNotification;

public class OutOfRangeGpsRuleNotificationDTO extends GpsRuleNotificationDTO{
	
	private String latitude;
	
	private String longitude;
	
	private OutOfRangeGpsRuleDTO outOfRangeGpsRule;


	public OutOfRangeGpsRuleNotificationDTO(OutOfRangeGpsRuleNotification outOfRangeGpsRuleNotification) {
		super(outOfRangeGpsRuleNotification);
		outOfRangeGpsRule = new OutOfRangeGpsRuleDTO(outOfRangeGpsRuleNotification.getOutOfRangeGpsRule());
		this.latitude = outOfRangeGpsRuleNotification.getLatitude();
		this.longitude = outOfRangeGpsRuleNotification.getLongitude();
	}

	public OutOfRangeGpsRuleDTO getOutOfRangeGpsRule() {
		return outOfRangeGpsRule;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	
}
