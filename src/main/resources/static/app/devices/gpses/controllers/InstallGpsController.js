
gpsesModule.controller("InstallGpsController",function(SystemAPIService, GpsAPIService, SweetAlertService, $route){

	let self = this;
	
	self.gps = new NewGps();
	
	self.deviceId;
	
	let fetchGpsCapabilities = function(){
		SystemAPIService.getGpsesCapabilities()
		.then(function(gpsCapabilities) { 
			self.supportedInterfaces = gpsCapabilities.getSupportedInterfaces();
			self.supportedWatchdogIntervals = gpsCapabilities.getSupportedWatchdogIntervals();
		},function() {
		})
	}
	
	let initializeController = function() {
		fetchGpsCapabilities();
	}
	
	initializeController();
	
	self.setGpsInterfaceType = function(interfaceType) {
		self.gps.setInterfaceType(interfaceType);
	}
	
	self.allRequired = function() {
		let interfaceDataIsSet = false;
		if (self.gps.interfaceTypeIsMQTT()) {
			interfaceDataIsSet = true;
		}
		if (self.gps.interfaceTypeIsTTN()) {
			interfaceDataIsSet = new RegExp("^[a-z0-9\\-]{3,50}$").test(this.deviceId || '');
		}
		let transductorDataIsSet = self.gps.isValid();
		return (transductorDataIsSet && interfaceDataIsSet) 
	}

	self.installAndRedirect = function() {
		let promise;
		if (self.gps.interfaceTypeIsMQTT()) {
			promise = GpsAPIService.installMqttGps(self.gps);
		}
		if (self.gps.interfaceTypeIsTTN()) {
			promise = GpsAPIService.installTTNGps(self.gps,  this.deviceId);
		}
		promise.then(function() {
			let redirectURL = $route.current.$$route.redirectURL;
			SweetAlertService.showSuccessAlertAndRedirect('GPS installed with success', redirectURL);
		},function(error) {
			SweetAlertService.showErrorAlert('GPS installation failed' + ' \n Error: ' + error.data.message);
		})
	}

});
