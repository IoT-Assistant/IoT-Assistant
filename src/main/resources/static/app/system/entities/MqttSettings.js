class MqttSettings {

	constructor(broker, clientId, username, password) {
		this.broker = broker;
		this.clientId = clientId;
		this.username = username;
		this.password = password;
    }
    
    getBroker() {
		return this.broker;
	}
	
	getClientId() {
		return this.clientId;
	}
	
	getUsername() {
		return this.username;
	}
	
	getPassword() {
		return this.password;
	}
}