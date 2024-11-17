package com.iotassistant.models.notifications;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class GpsRuleNotification extends Notification{
	

	public abstract Integer getGpsRuleId();

	public GpsRuleNotification() {
		super();
	}

	public GpsRuleNotification(String date) {
		super(date);
	}
	
	public abstract String getGpsName() ;
}
