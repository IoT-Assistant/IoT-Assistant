
sensorsModule.service ("SensorAPIService",function(RestAPIService, $q){
	let self = this;	
	
	let sensorsBaseUri = "sensors/";

	self.getSensors = function () {
		let deferred = $q.defer();
		RestAPIService.get(sensorsBaseUri).then(function(objectResponse) {
			deferred.resolve(getSensorsFromResponse(objectResponse));
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	

	function getSensorsFromResponse(objectResponse) {
		let sensors = [];
		objectResponse.sensors.forEach(sensorObject => {
			let propertiesMeasured = [];
			sensorObject.propertiesMeasured.forEach(propertyMeasuredObject => {
				let propertyMeasured = PropertyMapper.map(propertyMeasuredObject);
				propertiesMeasured.push(propertyMeasured);
			})
			let sensorValues = null;		
			if (sensorObject.active) {
				let values = [];
				for (const [propertyMeasured, value] of Object.entries(sensorObject.sensorValues.values)) {
					values[propertyMeasured] = new SensorValue(value.string, value.unit, value.description, value.severity);
				}
				sensorValues = new SensorValues(sensorObject.sensorValues.time, values);
			}
			let sensor = new Sensor(sensorObject.name, sensorObject.description, sensorObject.active, sensorValues, propertiesMeasured, sensorObject.watchdogInterval, sensorObject.watchdogEnabled);
			sensors.push(sensor);
		})
		return sensors;
	}
	
	
	self.installMQttInterfaceSensor = function (newSensor) {
		let deferred = $q.defer();
		let newMqttInterfaceSensor = createNewMqttInterfaceSensorObjRequest(newSensor);
		RestAPIService.post(sensorsBaseUri.concat("mqtt-interface-sensors/"), newMqttInterfaceSensor).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	function createNewMqttInterfaceSensorObjRequest(newSensor) {
		return createSensorObjectRequest(newSensor);
	}
	
	function createSensorObjectRequest(newSensor) {
		let newSensorObject = {};
		newSensorObject.name = newSensor.getName();
		newSensorObject.description = newSensor.getDescription();
		newSensorObject.watchdogInterval = newSensor.getWatchdogInterval();
		newSensorObject.propertiesMeasured = newSensor.getPropertiesNames();
		return newSensorObject;
	}
	
	self.installTTNInterfaceSensor = function (newSensor, deviceId) {
		let deferred = $q.defer();
		let newTTNInterfaceSensor = createNewTTNInterfaceSensorObjRequest(newSensor, deviceId);
		RestAPIService.post(sensorsBaseUri.concat("ttn-interface-sensors/"), newTTNInterfaceSensor).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	function createNewTTNInterfaceSensorObjRequest(newSensor, deviceId) {
		let newTTNInterfaceSensor = createSensorObjectRequest(newSensor);
		newTTNInterfaceSensor.deviceId = deviceId;
		return newTTNInterfaceSensor;
	}
	
	self.deleteSensor = function (name) {
		let deferred = $q.defer();
		RestAPIService.delete(sensorsBaseUri.concat(name)).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	self.enableWatchdog = function (enable, sensorName) {
		let deferred = $q.defer();
		let watchdogEnableRequestObject = {};
		watchdogEnableRequestObject.enable = enable;
		RestAPIService.patch(sensorsBaseUri.concat(sensorName), watchdogEnableRequestObject).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	

});

