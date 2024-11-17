
gpsesModule.service ("GpsAPIService",function(RestAPIService, $q){
	let self = this;	
	
	let gpsBaseUri = "gpses/";

	self.getGpses = function () {
		let deferred = $q.defer();
		RestAPIService.get(gpsBaseUri).then(function(objectResponse) {
			deferred.resolve(getGPSsFromResponse(objectResponse));
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}

	function getGPSsFromResponse(objectResponse) {
		let gpss = [];
		objectResponse.gpses.forEach(gpsObject => {
			let position = null;		
			if (gpsObject.active) {
				position = new GpsPosition(gpsObject.position.longitude, gpsObject.position.latitude,  gpsObject.position.time);
			}
			let gps = new Gps(gpsObject.name, gpsObject.description, gpsObject.active, gpsObject.watchdogInterval, gpsObject.watchdogEnabled, position);
			gpss.push(gps);
		})
		return gpss;
	}
	
	
	self.installMqttGps = function (newGps) {
		let deferred = $q.defer();
		let newMQTTGPSObjRequest= createNewMQTTGPSObjRequest(newGps);
		RestAPIService.post(gpsBaseUri.concat("mqtt-interface-gpses/"), newMQTTGPSObjRequest).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	self.installTTNGps = function (newGps, deviceId) {
		let deferred = $q.defer();
		let newTTNGPSObjRequest= createNewTTNGPSObjRequest(newGps, deviceId);
		RestAPIService.post(gpsBaseUri.concat("ttn-interface-gpses/"), newTTNGPSObjRequest).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	function createNewMQTTGPSObjRequest(newGps) {
		return createGpsObjectRequest(newGps);
	}
	
	function createGpsObjectRequest(newGps) {
		let newGpsObject = {};
		newGpsObject.name = newGps.getName();
		newGpsObject.description = newGps.getDescription();
		newGpsObject.watchdogInterval = newGps.getWatchdogInterval();
		return newGpsObject;
	}
	
	function createNewTTNGPSObjRequest(newGps, deviceId) {
		let newTTNGPSObjRequest = createGpsObjectRequest(newGps);
		newTTNGPSObjRequest.deviceId = deviceId;
		return newTTNGPSObjRequest;
	}
	

	self.deleteGps = function (name) {
		let deferred = $q.defer();
		RestAPIService.delete(gpsBaseUri.concat(name)).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	self.enableWatchdog = function (enable, gpsName) {
		let deferred = $q.defer();
		let watchdogEnableRequestObject = {};
		watchdogEnableRequestObject.enable = enable;
		RestAPIService.patch(gpsBaseUri.concat(gpsName + '/watchdog'), watchdogEnableRequestObject).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}


});

