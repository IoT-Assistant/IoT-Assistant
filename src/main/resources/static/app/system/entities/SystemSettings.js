class SystemSettings {

	constructor(security, mqtt, ttn, telegram) {
		this.security = security;
		this.mqtt = mqtt;
		this.ttn = ttn;
		this.telegram = telegram;
    }
	
	getSecuritySettings() {
		return this.security;
	}

	
	getMqttSettings() {
		return this.mqtt;
	}
	
	getTTNSettings() {
		return this.ttn;
	}
	
	getTelegramSettings() {
		return this.telegram;
	}


}