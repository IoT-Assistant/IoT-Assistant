class DeviceOfflineNotification extends Notification{
		
	constructor(id, date, deviceName, watchdogInterval) {
		super(id, date);
		this.deviceName = deviceName;
		this.watchdogInterval = watchdogInterval;
	}
	
	toString() {
		var deviceName = this.deviceName;
		var text = "Component " + deviceName + " went offline" + ". Date: " + this.date + ", watchddog interval: " + this.watchdogInterval;
		return text;
	}
	
	
}