package com.iotassistant.controllers.dtos.notifications;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotassistant.models.notifications.DeviceOfflineNotification;
import com.iotassistant.models.notifications.Notification;
import com.iotassistant.models.notifications.NotificationVisitor;
import com.iotassistant.models.notifications.OutOfRangeGpsRuleNotification;
import com.iotassistant.models.notifications.SensorRuleAlarmNotification;
import com.iotassistant.models.notifications.SensorRuleCameraNotification;
import com.iotassistant.models.notifications.SensorRuleEnableRuleNotification;
import com.iotassistant.models.notifications.SensorRuleTriggerActuatorNotification;

public class NotificationsDTO implements NotificationVisitor{
	
	private ArrayList<SensorAlarmNotificationDTO> sensorAlarmNotifications = new ArrayList<SensorAlarmNotificationDTO>();
	
	private ArrayList<DeviceOfflineNotificationDTO> offlineNotifications = new ArrayList<DeviceOfflineNotificationDTO>();
	
	private ArrayList<SensorRuleEnableRuleNotificationDTO> sensorRuleEnableRuleNotifications = new ArrayList<SensorRuleEnableRuleNotificationDTO>();

	private ArrayList<SensorRuleTriggerActuatorNotificationDTO> sensorRuleTriggerActuatorNotifications = new ArrayList<SensorRuleTriggerActuatorNotificationDTO>();

	private ArrayList<SensorRuleCameraNotificationDTO> sensorRuleCameraNotifications = new ArrayList<SensorRuleCameraNotificationDTO>();
	
	private ArrayList<OutOfRangeGpsRuleNotificationDTO> outOfRangeGpsRuleNotifications = new ArrayList<OutOfRangeGpsRuleNotificationDTO>();


	private String baseUrlNotificationsAttachment;
	
	public NotificationsDTO() {
		super();
	}

	public NotificationsDTO(List<Notification> allNotifications, String baseUrlNotificationsAttachment) {
		super();
		this.baseUrlNotificationsAttachment = baseUrlNotificationsAttachment;
		for (Notification notification: allNotifications) {
			notification.accept(this);
		}
	}

	public ArrayList<SensorAlarmNotificationDTO> getSensorAlarmNotifications() {
		return sensorAlarmNotifications;
	}

	public ArrayList<DeviceOfflineNotificationDTO> getOfflineNotifications() {
		return offlineNotifications;
	}

	public ArrayList<SensorRuleEnableRuleNotificationDTO> getSensorRuleEnableRuleNotifications() {
		return sensorRuleEnableRuleNotifications;
	}

	public ArrayList<SensorRuleTriggerActuatorNotificationDTO> getSensorRuleTriggerActuatorNotifications() {
		return sensorRuleTriggerActuatorNotifications;
	}
	

	public ArrayList<SensorRuleCameraNotificationDTO> getSensorRuleCameraNotifications() {
		return sensorRuleCameraNotifications;
	}
	

	public ArrayList<OutOfRangeGpsRuleNotificationDTO> getOutOfRangeGpsRuleNotifications() {
		return outOfRangeGpsRuleNotifications;
	}

	@JsonIgnore
	@Override
	public void visit(SensorRuleAlarmNotification sensorRuleAlarmNotification) {
		this.sensorAlarmNotifications.add(new SensorAlarmNotificationDTO(sensorRuleAlarmNotification));	
	}

	@JsonIgnore
	@Override
	public void visit(SensorRuleEnableRuleNotification sensorRuleEnableRuleNotification) {
		this.sensorRuleEnableRuleNotifications.add(new SensorRuleEnableRuleNotificationDTO(sensorRuleEnableRuleNotification));		
	}

	@JsonIgnore
	@Override
	public void visit(SensorRuleTriggerActuatorNotification sensorRuleTriggerActuatorNotification) {
		this.sensorRuleTriggerActuatorNotifications.add(new SensorRuleTriggerActuatorNotificationDTO(sensorRuleTriggerActuatorNotification));				
	}

	@JsonIgnore
	@Override
	public void visit(DeviceOfflineNotification offlineNotification) {
		this.offlineNotifications.add(new DeviceOfflineNotificationDTO(offlineNotification));	
	}

	@Override
	public void visit(SensorRuleCameraNotification sensorRuleCameraNotification) {
		this.sensorRuleCameraNotifications.add(new SensorRuleCameraNotificationDTO(sensorRuleCameraNotification, baseUrlNotificationsAttachment));
		
	}

	@Override
	public void visit(OutOfRangeGpsRuleNotification outOfRangeGpsRuleNotification) {
		this.outOfRangeGpsRuleNotifications.add(new OutOfRangeGpsRuleNotificationDTO(outOfRangeGpsRuleNotification));
		
	}

	
}
