package com.iotassistant.services.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.devices.transductors.Property;
import com.iotassistant.models.devices.transductors.TransductorInterfaceTypeEnum;
import com.iotassistant.services.CamerasService;
import com.iotassistant.services.ChartsService;
import com.iotassistant.services.GpsesService;
import com.iotassistant.services.NotificationsService;
import com.iotassistant.services.RulesFacadeService;
import com.iotassistant.services.TransductorsFacadeService;

@Service
public class SystemCapabilitiesService {
	
	@Autowired
	private TransductorsFacadeService transductorsService;
	
	@Autowired
	private CamerasService camerasService;
	
	@Autowired
	private GpsesService gpsesService;

	private @Autowired
	NotificationsService notificationsService;
	
	private @Autowired
	RulesFacadeService rulesService;
	
	@Autowired
	private ChartsService chartsService;
	
	
	public List<Property> getSupportedSensorProperties() {
		return transductorsService.getSupportedPropertiesMeasured();
	}

	public List<String> getSupportedSensorInterfaces() {
		return this.transductorsService.getSupportedTransductorInterfaces();
	}

	public List<Property> getSupportedActuatorProperties() {
		return transductorsService.getSupportedPropertiesActuated();
	}

	public List<String> getSupportedActuatorInterfaces() {
		List<String> supportedActuatorInterfaces = new ArrayList<String>();
		supportedActuatorInterfaces.add(TransductorInterfaceTypeEnum.MQTT.toString());
		return supportedActuatorInterfaces;
	}

	public List<String> getSupportedNotificationTypes() {
		return notificationsService.getAvailableNotificationTypes();
	}
	
	public List<String> getSupportedSensorRuleTriggerIntervalEnum() {
		return rulesService.getSupportedRulesTriggerInterval();
	}
	

	public List<String> getSupportedChartTypes() {
		return chartsService.getSupportedChartTypes();
	}

	public List<String> getSupportedChartIntervals() {
		return chartsService.getSupportedChartIntervals();
	}

	public List<String> getSupportedSampleIntervals() {
		return chartsService.getSupportedSampleIntervals();
	}

	public List<String> getSupportedTransductorsWatchdogIntervals() {
		return transductorsService.getSupportedWatchdogIntervals();
	}
	
	public List<String> getSupportedCamerasWatchdogIntervals() {
		return camerasService.getSupportedWatchdogIntervals();
	}
	

	public List<String> getSupportedGpsesInterfaces() {
		return gpsesService.getSupportedInterfaces();
	}

	public List<String> getSupportedGpsesWatchdogIntervals() {
		return gpsesService.getSupportedWatchdogIntervals();
	}


	public List<String> getSupportedSensorRulesTypes() {
		return rulesService.getSupportedSensorRulesTypes();
	}


	public List<String> getSupportedRulesTriggerInterval() {
		return rulesService.getSupportedRulesTriggerInterval();
	}

	public List<String> getSupportedCameraInterfaces() {
		return camerasService.getSupportedInterfaces();
	}


	public List<String> getSupportedGpsRulesTypes() {
		return rulesService.getSupportedGpsRulesTypes();
	}

}
