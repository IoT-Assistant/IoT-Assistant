
gpsRulesModule.controller ("InstallGpsRuleController", function($scope, $controller, GpsAPIService, SystemAPIService, SweetAlertService, $route){

	let self = this;
	
	self.gpses = [];
	
	self.gpsRule = {gpsName: null, gpsRuleType : null, timeBetweenTriggers: null, notificationType: null};	
	
	self.installOutOfRangeGpsRuleController = $controller('InstallOutOfRangeGpsRuleController', {$scope: $scope.$new()});

	self.rulesCapabilities;
	
	self.supportedNotificationsTypes;
	
	let fetchGpses = function(){
		GpsAPIService.getGpses()
		.then(function(gpses) { 
			self.gpses = gpses;
		},function() {
			self.gpses = [];
		})
	}
	
	let fetchGpsRulesCapabilities = function(){
		SystemAPIService.getRulesCapabilities()
		.then(function(rulesCapabilities) { 
			self.rulesCapabilities = rulesCapabilities;
		},function() {
		})
	}
	
	let fetchNotificationsCapabilities = function(){
		SystemAPIService.getNotificationsCapabilities()
		.then(function(notificationsCapabilities) { 
			self.supportedNotificationsTypes = notificationsCapabilities.getSupportedNotificationsTypes();
		},function() {
		})
	}
	
	let initializeController = function() {
		fetchGpses();
		fetchGpsRulesCapabilities();
		fetchNotificationsCapabilities();
	}
	
	initializeController();
	
	self.setGpsRuleType = function(gpsRuleType) {
		self.gpsRule.gpsRuleType = gpsRuleType;
	}
	
	
	self.allRequired = function() {
		if (self.gpsRule.gpsRuleType != null) {		
			return getGpsRuleController().allRequired(self.gpsRule);
		}	
		return false;
	}
	
	
	let getGpsRuleController = function() {
		if (self.isOutOfRangeGpsRuleTypeSelected()){
			return self.installOutOfRangeGpsRuleController;
		}
	}
	
	
	self.isOutOfRangeGpsRuleTypeSelected = function() {
		return self.gpsRule.gpsRuleType == OutOfRangeGpsRule.typeString;
	}
	
	self.installAndRedirect = function() {
		let controller = getGpsRuleController(self.gpsRule.gpsRuleType);	
		let promise = controller.install(self.gpsRule);
		promise.then(function() {
			let redirectURL = $route.current.$$route.redirectURL;
			SweetAlertService.showSuccessAlertAndRedirect('Gps rule installed with success', redirectURL);
		},function(error) {
			SweetAlertService.showErrorAlert('Gps rule installation failed' + ' \n Error: ' + error.data.message);
		})		
	}

});
