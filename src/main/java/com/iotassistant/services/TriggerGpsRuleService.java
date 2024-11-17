package com.iotassistant.services;

import java.text.ParseException;

import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.models.notifications.GpsRuleNotification;
import com.iotassistant.models.notifications.OutOfRangeGpsRuleNotification;
import com.iotassistant.models.rules.GpsRule;
import com.iotassistant.models.rules.GpsRuleVisitor;
import com.iotassistant.models.rules.OutOfRangeGpsRule;
import com.iotassistant.utils.Date;

public class TriggerGpsRuleService implements GpsRuleVisitor{
	
	private GpsRule gpsRule;
	
	private GpsPosition position;

	public TriggerGpsRuleService(GpsRule gpsRule, GpsPosition position) {
		super();
		this.gpsRule = gpsRule;
		this.position = position;
	}

	public void trigger() {
		if (this.isTriggerIntervalReached()) {
			gpsRule.accept(this);
		}
		
	}
	
	private boolean isTriggerIntervalReached() {
		try {
			GpsRuleNotification lastNotification = getLastNotification(this.gpsRule.getId());
			return (lastNotification == null) ? true : Date.havePassedMinutesBetweenDates(lastNotification.getDate(), position.getDate(), gpsRule.getTimeBetweenTriggers().getMinutes());
		} catch (ParseException e) {
			return false;
		}
		
	}
	
	private GpsRuleNotification getLastNotification(Integer gpsRuleId) {
		for(GpsRuleNotification gpsRuleNotification: NotificationsService.getInstance().getGpsRulesNotificationsByIdDesc()) {
			if (gpsRuleNotification.getGpsRuleId().equals(gpsRuleId)) {
				return gpsRuleNotification;
			}
		}
		return null;
	}

	@Override
	public void visit(OutOfRangeGpsRule outOfRangeGpsRule) {
		OutOfRangeGpsRuleNotification outOfRangeGpsRuleNotification = new OutOfRangeGpsRuleNotification(outOfRangeGpsRule, position, position.getDate());
		NotificationsService.getInstance().sendNotification(outOfRangeGpsRule.getNotificationType(), outOfRangeGpsRuleNotification);
	
	}

}
