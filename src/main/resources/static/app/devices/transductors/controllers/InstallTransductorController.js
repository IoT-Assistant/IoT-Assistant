
transductorsModule.controller ("InstallTransductorController",function(SystemAPIService, SweetAlertService, $route){

	let self = this;

	self.transductor = new NewTransductor();
	
	self.deviceId;
	
	self.systemAPIService = SystemAPIService;

	self.setTransductorInterface = function(interfaceType) {
		self.transductor.setInterfaceType(interfaceType);
	}

	self.allRequired = function() {
		let interfaceDataIsSet = false;
		if (self.transductor.interfaceTypeIsMQTT()) {
			interfaceDataIsSet = true;
		}
		if (self.transductor.interfaceTypeIsTTN()) {
			interfaceDataIsSet = new RegExp("^[a-z0-9\\-]{3,50}$").test(this.deviceId || '');
		}
		let transductorDataIsSet = (!(self.transductor.getName() == null) && !(self.transductor.getDescription() == null) && !(self.transductor.getWatchdogInterval() == null));
		return (transductorDataIsSet && interfaceDataIsSet) 
	}

	self.installAndRedirect = function(withSwal) {
		let promise;
		if (self.transductor.interfaceTypeIsMQTT()) {
			promise = this.installMQttInterfaceTransductor(self.transductor);
		} else if (self.transductor.interfaceTypeIsTTN()) {
			promise = this.installTTNInterfaceTransductor(self.transductor, this.deviceId);
		}
		self.transductorType =  this.transductorType;
		promise.then(function() {
			let redirectURL = $route.current.$$route.redirectURL;
			SweetAlertService.showSuccessAlertAndRedirect(self.transductorType + ' installed with success', redirectURL);
		},function(error) {
			SweetAlertService.showErrorAlert(self.transductorType + ' installation failed' + ' \n Error: ' + error.data.message);
		})
	}

});
