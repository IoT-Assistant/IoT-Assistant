package com.iotassistant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.devices.WatchdogInterval;
import com.iotassistant.models.devices.transductors.Actuator;
import com.iotassistant.models.devices.transductors.ActuatorValues;
import com.iotassistant.models.devices.transductors.Property;
import com.iotassistant.models.devices.transductors.Sensor;
import com.iotassistant.models.devices.transductors.SensorValues;
import com.iotassistant.models.devices.transductors.Transductor;
import com.iotassistant.models.devices.transductors.TransductorInterfaceTypeEnum;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.repositories.TransductorsJPARepository;


@Service
public class TransductorsFacadeService {
	
	@Autowired
	private SensorsService sensorsService;
	
	@Autowired
	private ActuatorsService actuatorsService;
	
	@Autowired
	private TransductorsJPARepository transductorsJPARepository;
	

	
	public List<Property> getSupportedPropertiesMeasured() {
		return sensorsService.getSupportedPropertiesMeasured();
	}


	public List<Property> getSupportedPropertiesActuated() {
		return actuatorsService.getSupportedPropertiesActuated();
	}


	public List<Transductor> getAllTransductors() {
		return transductorsJPARepository.findAll();
	}

	public List<String> getSupportedWatchdogIntervals() {
		return WatchdogInterval.getAvailableWatchdogIntervalOptions();
	}

	public void setUpInterface(Sensor sensor)  {
		sensorsService.setUpInterface(sensor);		
	}

	public void setUpInterface(Actuator actuator) {
		actuatorsService.setUpInterface(actuator);		
	}

	public Transductor getTransductorByName(String name) {
		Optional<Transductor> transductor = transductorsJPARepository.findById(name);
		return transductor.isPresent() ? transductor.get() : null;
	
	}


	public void updateSensorValues(String name, SensorValues sensorValues) {
		sensorsService.update(name, sensorValues);	
	}


	public void updateActuatorValues(String name, ActuatorValues actuatorValues) {
		actuatorsService.update(name, actuatorValues);
		
	}


	public List<String> getSupportedTransductorInterfaces() {
		return TransductorInterfaceTypeEnum.getAllInstances();
	}


	public boolean sensorHasProperty(String sensorName, PropertyMeasuredEnum propertyObserved) {
		return sensorsService.sensorHasProperty(sensorName, propertyObserved);
	}


	public boolean existSensor(String sensorName) {
		return sensorsService.exist(sensorName);
	}


	public boolean existActuator(String actuatorName) {
		return actuatorsService.exist(actuatorName);
	}


}
