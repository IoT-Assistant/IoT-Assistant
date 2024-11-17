package com.iotassistant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.TelegramBotManager;
import com.iotassistant.models.notifications.DeviceOfflineNotification;
import com.iotassistant.models.notifications.OutOfRangeGpsRuleNotification;
import com.iotassistant.models.notifications.SendNotificationService;
import com.iotassistant.models.notifications.SensorMeasureNotification;
import com.iotassistant.models.notifications.SensorRuleAlarmNotification;
import com.iotassistant.models.notifications.SensorRuleCameraNotification;
import com.iotassistant.models.notifications.SensorRuleEnableRuleNotification;
import com.iotassistant.models.notifications.SensorRuleTriggerActuatorNotification;

@Component
public class SendNotificationTelegramService extends SendNotificationService {
	
	private static final String TELEGRAM_MSG_END_OF_LINE = "\n";
	
	private static final String TELEGRAM_MSG_BOLD_TAG = "*";
	
	private static final String TELEGRAM_MSG_MONOSPACE_TAG = "`";

	private @Autowired
	TelegramBotManager telegramBotManager;
	
	
	@Override
	public void visit(SensorRuleAlarmNotification sensorMeasureNotification)  {	
		try {
			String alarmTelegramNotificationMessage = buildTelegramAlarmSensorRuleNotificationMessage(sensorMeasureNotification);
			telegramBotManager.sendTelegramTextMessage(alarmTelegramNotificationMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private String buildTelegramAlarmSensorRuleNotificationMessage(SensorRuleAlarmNotification alarmNotification)  {			
		String alarmNotificationText = buildTelegramSensorMeasureCommonMessage("SENSOR ALARM", alarmNotification);
		alarmNotificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		return alarmNotificationText;
	}
	

	@Override
	public void visit(DeviceOfflineNotification offlineNotification) {
		try {
			String offlineTelegramNotificationMessage = buildTelegramOfflineNotificationMessage(offlineNotification);
			telegramBotManager.sendTelegramTextMessage(offlineTelegramNotificationMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private String buildTelegramOfflineNotificationMessage(DeviceOfflineNotification offlineNotification)  {			
		String notificationText = TELEGRAM_MSG_BOLD_TAG + "DEVICE OFFLINE" + TELEGRAM_MSG_BOLD_TAG + TELEGRAM_MSG_END_OF_LINE;
		notificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		notificationText += "Device name: " + offlineNotification.getDeviceName() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Date: " + offlineNotification.getDate() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Watchdog interval: " + offlineNotification.getWatchdogInterval() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		return notificationText;
	}

	@Override
	public void visit(SensorRuleEnableRuleNotification sensorRuleEnableRuleNotification) {
		try {
			String sensorRuleSwitchAlarmTelegramNotificationMessage = buildTelegramEnableRuleSensorRuleNotificationMessage(sensorRuleEnableRuleNotification);
			telegramBotManager.sendTelegramTextMessage(sensorRuleSwitchAlarmTelegramNotificationMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String buildTelegramEnableRuleSensorRuleNotificationMessage(SensorRuleEnableRuleNotification sensorRuleEnableRuleNotification) {
		String newSensorRuleState = (sensorRuleEnableRuleNotification.isSensorRuleNewState())? "Enabled" : "Disabled";
		String notificationText = buildTelegramSensorMeasureCommonMessage(newSensorRuleState.toUpperCase() + " ENABLED RULE", sensorRuleEnableRuleNotification);
		notificationText += "Sensor rule ID: " + sensorRuleEnableRuleNotification.getEnableSensorRule().getRuleId() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "New sensor rule state: " + newSensorRuleState  + TELEGRAM_MSG_END_OF_LINE;
		notificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		return notificationText;
	}
	
	private String buildTelegramSensorMeasureCommonMessage(String notificationName, SensorMeasureNotification sensorMeasureNotification) {
		String notificationText = TELEGRAM_MSG_BOLD_TAG + notificationName + TELEGRAM_MSG_BOLD_TAG + TELEGRAM_MSG_END_OF_LINE;
		notificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		notificationText += "Sensor name: " + sensorMeasureNotification.getSensorName() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Property: " + sensorMeasureNotification.getPropertyObserved().getNameWithUnit() + TELEGRAM_MSG_END_OF_LINE;
		String analogThresholdOperator = (sensorMeasureNotification.getPropertyObserved().isBinary())? "" : sensorMeasureNotification.getValueThresholdOperator().toString(); 
		notificationText += "Value threshold: " + analogThresholdOperator + sensorMeasureNotification.getValueThresholdObserved() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Value: " + sensorMeasureNotification.getSensorValue() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Date: " + sensorMeasureNotification.getDate() + TELEGRAM_MSG_END_OF_LINE;
		return notificationText;
	}

	@Override
	public void visit(SensorRuleTriggerActuatorNotification sensorRuleTriggerActuatorNotification) {
		try {
			String sensorRuleTriggerActuatorTelegramNotificationMessage = buildTelegramTriggerActuatorSensorRuleNotificationMessage(sensorRuleTriggerActuatorNotification);
			telegramBotManager.sendTelegramTextMessage(sensorRuleTriggerActuatorTelegramNotificationMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String buildTelegramTriggerActuatorSensorRuleNotificationMessage(
			SensorRuleTriggerActuatorNotification sensorRuleTriggerActuatorNotification) {
		String notificationText = buildTelegramSensorMeasureCommonMessage("TRIGGERED ACTUATOR", sensorRuleTriggerActuatorNotification);
		notificationText += "Actuator name: " + sensorRuleTriggerActuatorNotification.getSensorRule().getActuatorName() + TELEGRAM_MSG_END_OF_LINE;
		String newActuatorValue = (sensorRuleTriggerActuatorNotification.getSensorRule().getActuatorSetValue());
		notificationText += "Actuator set value: " + newActuatorValue  + TELEGRAM_MSG_END_OF_LINE;
		notificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		return notificationText;
	}

	@Override
	public void visit(SensorRuleCameraNotification sensorRuleCameraNotification) {
		try {
			String sensorRuleCameraTelegramNotificationMessage = buildTelegramCameraSensorRuleNotificationMessage(sensorRuleCameraNotification);
			telegramBotManager.sendTelegramTextMessage(sensorRuleCameraTelegramNotificationMessage);
			if (sensorRuleCameraNotification.getPicture() != null) {
				telegramBotManager.sendTelegramPictureMessage("Picture", sensorRuleCameraNotification.getPicture());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String buildTelegramCameraSensorRuleNotificationMessage(
			SensorRuleCameraNotification sensorRuleCameraNotification) {
		String notificationText = buildTelegramSensorMeasureCommonMessage("CAMERA PICTURE", sensorRuleCameraNotification);
		notificationText += "Camera name: " + sensorRuleCameraNotification.getCameraSensorRule().getCameraName() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Picture: "  + sensorRuleCameraNotification.getPicture() == null ? "Could not get image from camera" : "" + TELEGRAM_MSG_END_OF_LINE;
		notificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		return notificationText;
	}

	@Override
	public void visit(OutOfRangeGpsRuleNotification outOfRangeGpsNotification) {
		try {
			String offlineTelegramNotificationMessage = buildTelegramOutOfRangeGpsNotificationMessage(outOfRangeGpsNotification);
			telegramBotManager.sendTelegramTextMessage(offlineTelegramNotificationMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private String buildTelegramOutOfRangeGpsNotificationMessage(OutOfRangeGpsRuleNotification outOfRangeGpsNotification)  {			
		String notificationText = TELEGRAM_MSG_BOLD_TAG + "GPS OUT OF RANGE" + TELEGRAM_MSG_BOLD_TAG + TELEGRAM_MSG_END_OF_LINE;
		notificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		notificationText += "GPS name: " + outOfRangeGpsNotification.getGpsName() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Date: " + outOfRangeGpsNotification.getDate() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Reference position: " + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "	Latitude: " +  outOfRangeGpsNotification.getRuleLatitude() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "	Longitude: " + outOfRangeGpsNotification.getRuleLongitude() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "	Range: " + outOfRangeGpsNotification.getRange() + "m" + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "Current position: " + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "	Latitude: " +  outOfRangeGpsNotification.getLatitude() + TELEGRAM_MSG_END_OF_LINE;
		notificationText += "	Longitude: " + outOfRangeGpsNotification.getLongitude() + TELEGRAM_MSG_END_OF_LINE;
		String mapLink = "https://maps.google.com/maps?q=" + outOfRangeGpsNotification.getLatitude() + ',' + outOfRangeGpsNotification.getLongitude() + "&hl=es;z=14&amp;output=embed";
		notificationText += TELEGRAM_MSG_MONOSPACE_TAG;
		notificationText += "Link: " + "[Open map]("+mapLink+")" + TELEGRAM_MSG_END_OF_LINE;
		return notificationText;
	}

}
