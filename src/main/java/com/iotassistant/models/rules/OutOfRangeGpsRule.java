package com.iotassistant.models.rules;

import javax.persistence.Entity;

import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.models.notifications.NotificationTypeEnum;

@Entity
public class OutOfRangeGpsRule extends GpsRule{
	
	private String latitude;
	
	private String longitude;
	
	private int range;
	
	public OutOfRangeGpsRule() {
		super();
	}

	public OutOfRangeGpsRule(String gpsName, boolean enabled, RuleTriggerIntervalEnum timeBetweenTriggers,
			NotificationTypeEnum notificationType, String latitude, String longitude, int range) {
		super(gpsName, enabled, timeBetweenTriggers, notificationType);
		this.latitude = latitude;
		this.longitude = longitude;
		this.range = range;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public int getRange() {
		return range;
	}

	@Override
	public void accept(GpsRuleVisitor gpsRuleVisitor) {
		gpsRuleVisitor.visit(this);	
	}

	@Override
	public boolean apply(String gpsName, GpsPosition position) {
		return this.getGpsName().equals(gpsName) && calculateDistanceInMeters(position) >= range;
	}
	
	public double calculateDistanceInMeters(GpsPosition position) {
		return org.apache.lucene.util.SloppyMath.haversinMeters(
				Float.parseFloat(this.latitude), 
				Float.parseFloat(this.longitude), 
				Float.parseFloat(position.getLatitude()), 
				Float.parseFloat(position.getLongitude()));
}


}
