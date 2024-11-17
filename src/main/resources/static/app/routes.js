let sensorsRoute = '/sensors';
let actuatorsRoute = '/actuators';
let camerasRoute = '/cameras';
let gpsesRoute = '/gpses';
let notificationsRoute = '/notifications';
let chartsRoute = '/charts';
let rulesRoute = '/rules';
let installSensorRoute = '/installSensor';
let installActuatorRoute = '/installActuator';
let installChartRoute = '/installChart';
let installGpsRoute = '/installGps';
let installCameraRoute = '/installCamera';
let installSensorRuleRoute = '/installSensorRule';
let installGpsRuleRoute = '/installGpsRule';
let systemInformationRoute = '/systemInformation';
let systemSettingsRoute = '/systemSettings';
let systemPowerOffRoute = '/systemPowerOff';


iotAssistant.config(function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl : '../devices/transductors/sensors/sensors.html',
		controller : 'GetSensorsController',
		controllerAs : 'GetSensorsController'
	})
	.when(sensorsRoute, {
		templateUrl : '../devices/transductors/sensors/sensors.html',
		controller : 'GetSensorsController',
		controllerAs : 'GetSensorsController'
	})
	.when(actuatorsRoute, {
		templateUrl : '../devices/transductors/actuators/actuators.html',
		controller : 'GetActuatorsController',
		controllerAs : 'GetActuatorsController'
	})
	.when(camerasRoute, {
		templateUrl : '../devices/cameras/cameras.html',
		controller : 'GetCamerasController',
		controllerAs : 'GetCamerasController'
	})	
	.when(gpsesRoute, {
		templateUrl : '../devices/gpses/gpses.html',
		controller : 'GetGpsesController',
		controllerAs : 'GetGpsesController'
	})	
	.when(notificationsRoute, {
		templateUrl : '../notifications/notifications.html',
		controller : 'GetNotificationsController',
		controllerAs : 'GetNotificationsController'
	})
	.when(chartsRoute, {
		templateUrl : '../charts/charts.html',
		controller : 'GetChartsController',
		controllerAs : 'GetChartsController'
	})
	.when(rulesRoute, {
		templateUrl : '../rules/rules.html',
		controller : 'GetRulesController',
		controllerAs : 'GetRulesController'
	})
	.when(installSensorRoute, {
		templateUrl : '../devices/transductors/transductorInstallation.html',
		controller : 'InstallSensorController',
		controllerAs: "InstallTransductorController",
		redirectURL: sensorsRoute
	})
	.when(installActuatorRoute, {
		templateUrl : '../devices/transductors/transductorInstallation.html',
		controller : 'InstallActuatorController',
		controllerAs: "InstallTransductorController",
		redirectURL: actuatorsRoute
	})
	.when(installGpsRoute, {
		templateUrl : '../devices/gpses/gpsInstallation.html',
		controller : 'InstallGpsController',
		controllerAs : 'InstallGpsController',
		redirectURL: gpsesRoute
	})
	.when(installChartRoute, {
		templateUrl : '../charts/chartInstallation.html',
		controller : 'InstallChartController',
		controllerAs : 'InstallChartController',
		redirectURL: chartsRoute
	})
	.when(installSensorRuleRoute, {
		templateUrl : '../rules/sensorrules/sensorRuleInstallation.html',
		controller : 'InstallSensorRuleController',
		controllerAs : 'InstallSensorRuleController',
		redirectURL: rulesRoute
	})
	.when(installGpsRuleRoute, {
		templateUrl : '../rules/gpsrules/gpsRuleInstallation.html',
		controller : 'InstallGpsRuleController',
		controllerAs : 'InstallGpsRuleController',
		redirectURL: rulesRoute
	})
	.when(installCameraRoute, {
		templateUrl : '../devices/cameras/cameraInstallation.html',
		controller : 'InstallCameraController',
		controllerAs : 'InstallCameraController',
		redirectURL: camerasRoute
	})
	.when(systemInformationRoute, {
		templateUrl : '../system/systemInformation.html',
		controller : 'GetSystemInfoController',
		controllerAs: "GetSystemInfoController"
	})
	.when(systemSettingsRoute, {
		templateUrl : '../system/systemSettings.html',
		controller : 'SetSystemSettingsController',
		controllerAs: "SetSystemSettingsController",
		redirectURL: systemInformationRoute
	})
	.when(systemPowerOffRoute, {
		templateUrl : '../system/systemPowerOff.html',
		controller : 'PowerOffSystemController',
		controllerAs : 'PowerOffSystemController'
	})
});