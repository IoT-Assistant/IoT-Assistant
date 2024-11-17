package com.iotassistant.models.notifications;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.models.rules.OutOfRangeGpsRule;

@Entity
public class OutOfRangeGpsRuleNotification extends GpsRuleNotification {
	
	@NotNull
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private OutOfRangeGpsRule outOfRangeGpsRule;
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private GpsPosition position;

	public OutOfRangeGpsRuleNotification() {
		super();
	}
	
	public OutOfRangeGpsRuleNotification(OutOfRangeGpsRule outOfRangeGpsRule, GpsPosition position, String date) {
		super(date);
		this.position = position;
		this.outOfRangeGpsRule = outOfRangeGpsRule;
	}



	@Override
	public void accept(NotificationVisitor notificationVisitor) {
		notificationVisitor.visit(this);	
	}
	
	

	public OutOfRangeGpsRule getOutOfRangeGpsRule() {
		return outOfRangeGpsRule;
	}

	@Override
	public Integer getGpsRuleId() {
		return outOfRangeGpsRule.getId();
	}

	@Override
	public String getGpsName() {
		return outOfRangeGpsRule.getGpsName();
	}
	
	public int getRange() {
		return outOfRangeGpsRule.getRange();
	}
	
	public String getRuleLatitude() {
		return outOfRangeGpsRule.getLatitude();
	}
	
	public String getRuleLongitude() {
		return outOfRangeGpsRule.getLongitude();
	}
	
	public String getLatitude() {
		return position.getLatitude();
	}
	
	public String getLongitude() {
		return position.getLongitude();
	}

}
