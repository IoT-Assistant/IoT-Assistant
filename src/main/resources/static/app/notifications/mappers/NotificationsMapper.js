class NotificationsMapper {

	constructor() {
		this.rulesMapper = new RulesFacadeMapper();
	}
	
	buildNotificationsFromServiceObject(notificationsServiceObject) {		
		let offlineNotifications = this.#getOfflineNotificationsFromServiceObject(notificationsServiceObject.offlineNotifications);
		let sensorAlarmNotifications = this.#getSensorAlarmNotificationsFromServiceObject(notificationsServiceObject.sensorAlarmNotifications);
		let sensorEnableRuleNotifications = this.#getSensorEnableRuleNotificationsFromServiceObject(notificationsServiceObject.sensorRuleEnableRuleNotifications);
		let sensorRuleTriggerActuatorNotifications = this.#getSensorRuleTriggerActuatorNotificationsFromServiceObject(notificationsServiceObject.sensorRuleTriggerActuatorNotifications);
		let sensorRuleCameraNotifications = this.#getSensorRuleCameraNotificationsFromServiceObject(notificationsServiceObject.sensorRuleCameraNotifications);
		let outOfRangeGpsRuleNotifications = this.#getOutOfRangeGpsRuleNotifications(notificationsServiceObject.outOfRangeGpsRuleNotifications);
		return new Notifications(offlineNotifications, sensorAlarmNotifications, sensorEnableRuleNotifications, sensorRuleTriggerActuatorNotifications, sensorRuleCameraNotifications, outOfRangeGpsRuleNotifications);
	}
	
	#getOfflineNotificationsFromServiceObject(offlineNotificationsServiceObject) {
		let offlineNotifications = [];
		offlineNotificationsServiceObject.forEach(offlineNotificationObject => {
			let id = this.#getNotificationId(offlineNotificationObject);
			let date = this.#getNotificationDate(offlineNotificationObject);
			let deviceName = offlineNotificationObject.deviceName;
			let watchdogInterval = offlineNotificationObject.watchdogInterval;
			let notification = new DeviceOfflineNotification(id, date, deviceName, watchdogInterval);
			offlineNotifications.push(notification);
		})
		return offlineNotifications;
	}
	
	
	#getNotificationId(serviceNotificationObject) {
		return serviceNotificationObject.id;		
	}
	
	#getSensorAlarmNotificationsFromServiceObject(sensorAlarmNotificationsServiceObject) {
		let sensorAlarmNotifications = [];
		sensorAlarmNotificationsServiceObject.forEach(sensorAlarmNotificationObject => {
			let id = this.#getNotificationId(sensorAlarmNotificationObject);
			let date = this.#getNotificationDate(sensorAlarmNotificationObject);
			let alarmSensorRule = this.rulesMapper.buildAlarmSensorRuleFromServiceObject(sensorAlarmNotificationObject.alarmSensorRule);
			let sensorValue = this.#getSensorRuleNotificationSensorValue(sensorAlarmNotificationObject);
			let notification = new SensorRuleAlarmNotification(id, alarmSensorRule, sensorValue, date );
			sensorAlarmNotifications.push(notification);
		})
		return sensorAlarmNotifications;
	}
	
	#getNotificationDate(sensorRuleNotificationServiceObject) {
		return sensorRuleNotificationServiceObject.date;		
	}
	
	#getSensorEnableRuleNotificationsFromServiceObject(sensorRuleEnableRuleNotificationsServiceObject) {
		let sensorEnableRuleNotifications = [];
		sensorRuleEnableRuleNotificationsServiceObject.forEach(sensorRuleEnableRuleNotificationObject => {
			let id = this.#getNotificationId(sensorRuleEnableRuleNotificationObject);
			let date = this.#getNotificationDate(sensorRuleEnableRuleNotificationObject);
			let enableSensorRule = this.rulesMapper.buildEnableSensorRuleFromServiceObject(sensorRuleEnableRuleNotificationObject.enableRuleSensorRule);
			let sensorValue = this.#getSensorRuleNotificationSensorValue(sensorRuleEnableRuleNotificationObject);
			let newSensorRuleState = sensorRuleEnableRuleNotificationObject.newSensorRuleState;
			let notification = new SensorRuleEnableRuleNotification(id, enableSensorRule, sensorValue, date , newSensorRuleState);
			sensorEnableRuleNotifications.push(notification);
		})
		return sensorEnableRuleNotifications;
	}
	
	#getSensorRuleTriggerActuatorNotificationsFromServiceObject(sensorRuleTriggerActuatorNotificationsServiceObject) {
		let sensorRuleTriggerActuatorNotifications = [];
		sensorRuleTriggerActuatorNotificationsServiceObject.forEach(sensorRuleTriggerActuatorNotificationObject => {
			let id = this.#getNotificationId(sensorRuleTriggerActuatorNotificationObject);
			let date = this.#getNotificationDate(sensorRuleTriggerActuatorNotificationObject);
			let triggerActuatorSensorRule = this.rulesMapper.buildTriggerActuatorSensorRuleFromServiceObject(sensorRuleTriggerActuatorNotificationObject.triggerActuatorSensorRule);
			let sensorValue = this.#getSensorRuleNotificationSensorValue(sensorRuleTriggerActuatorNotificationObject);
			let notification = new SensorRuleTriggerActuatorNotification(id, triggerActuatorSensorRule, sensorValue, date  );
			sensorRuleTriggerActuatorNotifications.push(notification);
		})
		return sensorRuleTriggerActuatorNotifications;
	}

	#getSensorRuleNotificationSensorValue(sensorRuleNotificationServiceObject) {
		return sensorRuleNotificationServiceObject.sensorValue;		
	}
	
	#getSensorRuleCameraNotificationsFromServiceObject(sensorRuleCameraNotificationsServiceObject) {
		let sensorRuleCameraNotifications = [];
		sensorRuleCameraNotificationsServiceObject.forEach(sensorRuleCameraNotificationObject => {
			let id = this.#getNotificationId(sensorRuleCameraNotificationObject);
			let date = this.#getNotificationDate(sensorRuleCameraNotificationObject);
			let sensorValue = this.#getSensorRuleNotificationSensorValue(sensorRuleCameraNotificationObject);
			let cameraSensorRule = this.rulesMapper.buildCameraSensorRuleFromServiceObject(sensorRuleCameraNotificationObject.cameraSensorRule);
			let pictureURL = sensorRuleCameraNotificationObject.pictureURL;
			let notification = new SensorRuleCameraNotification(id, cameraSensorRule, sensorValue, date, pictureURL);
			sensorRuleCameraNotifications.push(notification);
		})
		return sensorRuleCameraNotifications;
	}
	
	#getOutOfRangeGpsRuleNotifications(outOfRangeGpsRuleNotificationsServiceObject) {
		let outOfRangeGpsRuleNotifications = [];
		outOfRangeGpsRuleNotificationsServiceObject.forEach(outOfRangeGpsRuleNotificationObject => {
			let id = this.#getNotificationId(outOfRangeGpsRuleNotificationObject);
			let date = this.#getNotificationDate(outOfRangeGpsRuleNotificationObject);
			let outOfRangeGpsRule = this.rulesMapper.buildOutOfRangeGpsRuleFromServiceObject(outOfRangeGpsRuleNotificationObject.outOfRangeGpsRule);
			let notification = new OutOfRangeGpsRuleNotification(id, outOfRangeGpsRule, date, outOfRangeGpsRuleNotificationObject.latitude, outOfRangeGpsRuleNotificationObject.longitude);
			outOfRangeGpsRuleNotifications.push(notification);
		})
		return outOfRangeGpsRuleNotifications;
	}
	
	

}