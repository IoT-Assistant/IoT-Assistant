class System {

	constructor(systemCapabilities, uptime) {
		this.capabilities = systemCapabilities;
		this.uptime = uptime;
    }
	
	getCapabilities() {
		return this.capabilities;
	}

	
	getUptime() {
		return this.uptime;
	}
	
	getServersStatus() {
		return this.capabilities.getServersStatus();
	}

}