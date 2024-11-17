package com.iotassistant.models.rules;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import com.iotassistant.models.notifications.NotificationTypeEnum;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Rule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Integer id;
	
	private boolean enabled;
	
	@Enumerated(EnumType.STRING)
	private RuleTriggerIntervalEnum timeBetweenTriggers;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private NotificationTypeEnum notificationType;
	

	public Rule() {
		super();
	}


	public Rule(boolean enabled, RuleTriggerIntervalEnum timeBetweenTriggers, NotificationTypeEnum notificationType) {
		super();
		this.enabled = enabled;
		this.timeBetweenTriggers = timeBetweenTriggers;
		this.notificationType = notificationType;
	}
	
	

	public Integer getId() {
		return id;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public RuleTriggerIntervalEnum getTimeBetweenTriggers() {
		return timeBetweenTriggers;
	}

	public void setTimeBetweenTriggers(RuleTriggerIntervalEnum timeBetweenTriggers) {
		this.timeBetweenTriggers = timeBetweenTriggers;
	}
	
	
	public NotificationTypeEnum getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationTypeEnum notificationType) {
		this.notificationType = notificationType;
	}
	
	public abstract void accept(RuleVisitor ruleVisitor);

}
