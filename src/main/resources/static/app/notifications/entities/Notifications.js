class Notifications {
	
	
	constructor(offlineNotifications, sensorAlarmNotifications, sensorEnableRuleNotifications, sensorRuleTriggerActuatorNotifications, cameraSensorRulesNotifications, outOfRangeGpsRuleNotifications) {
		this.offlineNotifications = offlineNotifications;
		this.sensorAlarmNotifications = sensorAlarmNotifications;
		this.sensorEnableRuleNotifications = sensorEnableRuleNotifications,
		this.sensorRuleTriggerActuatorNotifications = sensorRuleTriggerActuatorNotifications;
		this.cameraSensorRulesNotifications = cameraSensorRulesNotifications;
		this.outOfRangeGpsRuleNotifications = outOfRangeGpsRuleNotifications;
	}

	
	getAllNotificationsSortedByDate() {
		var notificationsSortedByDate = this.offlineNotifications;
		notificationsSortedByDate = notificationsSortedByDate.concat(this.sensorAlarmNotifications);
		notificationsSortedByDate = notificationsSortedByDate.concat(this.sensorEnableRuleNotifications);
		notificationsSortedByDate = notificationsSortedByDate.concat(this.sensorRuleTriggerActuatorNotifications);
		notificationsSortedByDate = notificationsSortedByDate.concat(this.cameraSensorRulesNotifications);
		notificationsSortedByDate = notificationsSortedByDate.concat(this.outOfRangeGpsRuleNotifications);
		notificationsSortedByDate.sort(function(a,b){
  			return new Date(b.date) - new Date(a.date);
		});
		return notificationsSortedByDate;
	}
	
	
	
}