package com.iotassistant.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotassistant.models.notifications.NotificationTypeEnum;
import com.iotassistant.models.rules.OutOfRangeGpsRule;
import com.iotassistant.models.rules.RuleTriggerIntervalEnum;

public class OutOfRangeGpsRuleDTO extends GpsRuleDTO{
	
	private String longitude;
	
	private String latitude;
	
	private int range;

	public OutOfRangeGpsRuleDTO() {
		super();
	}

	public OutOfRangeGpsRuleDTO(OutOfRangeGpsRule outOfRangeGpsRule) {
		super(outOfRangeGpsRule);
		this.longitude = outOfRangeGpsRule.getLongitude();
		this.latitude = outOfRangeGpsRule.getLatitude();
		this.range = outOfRangeGpsRule.getRange();
	}

	

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	@JsonIgnore
	public OutOfRangeGpsRule getOutOfRangeGpsRule() {
		RuleTriggerIntervalEnum timeBetweenTriggers = RuleTriggerIntervalEnum.getInstance(this.getTimeBetweenTriggers());
		NotificationTypeEnum notificationType = NotificationTypeEnum.getInstance(this.getNotificationType());
		return new OutOfRangeGpsRule(this.getGpsName(), this.isEnabled(), timeBetweenTriggers, notificationType, latitude, longitude, range);
	}
	

}
