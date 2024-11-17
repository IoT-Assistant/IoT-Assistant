package com.iotassistant.controllers.dtos;

import com.iotassistant.models.rules.Rule;

public class RuleDTO {
	
	private int id;
	
	private boolean enabled;
	
	private String timeBetweenTriggers;
	
	private String notificationType;

	public RuleDTO(Rule rule) {
		super();
		this.id = rule.getId();
		this.enabled = rule.isEnabled();
		this.timeBetweenTriggers = rule.getTimeBetweenTriggers().toString();
		this.notificationType = rule.getNotificationType().toString();
	}

	public RuleDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getTimeBetweenTriggers() {
		return timeBetweenTriggers;
	}

	public void setTimeBetweenTriggers(String timeBetweenTriggers) {
		this.timeBetweenTriggers = timeBetweenTriggers;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	
	

}
