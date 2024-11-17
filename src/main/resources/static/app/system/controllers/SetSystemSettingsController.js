systemModule.controller("SetSystemSettingsController",function(SystemAPIService, SweetAlertService, $route){

	let self = this;

	self.settings = null;

	let fetchSystemSettings = function(){
		SystemAPIService.getSystemSettings()
		.then(function(settings) { 
			self.settings = settings;	
		},function() {
		})
	}
	
	let initializeController = function() {
		fetchSystemSettings();   
	}

	initializeController(); 
	
	self.installAndRedirect = function() {
		let promise = SystemAPIService.setSystemSettings(self.settings);
		promise.then(function() {
			let redirectURL = $route.current.$$route.redirectURL;
			SweetAlertService.showSuccessAlertAndRedirect('Settings applied with success', redirectURL);
		},function(error) {
			SweetAlertService.showErrorAlert('Error' + ' \n Error: ' + error.data.message);
		})		
	}
	
	self.generateMqttClientId = function() {
		self.settings.mqtt.clientId = "iotAssistant-" + Date.now();
	}
	
	self.generateTTNClientId = function() {
		self.settings.ttn.clientId = "iotAssistant-" + Date.now();
	}

});
