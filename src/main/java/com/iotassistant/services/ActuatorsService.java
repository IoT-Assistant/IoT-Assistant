package com.iotassistant.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.devices.transductors.Actuator;
import com.iotassistant.models.devices.transductors.ActuatorValues;
import com.iotassistant.models.devices.transductors.Property;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;
import com.iotassistant.repositories.ActuatorsJPARepository;

@Service
public class ActuatorsService extends DeviceService {
	
	private static ActuatorsService instance;

	private @Autowired
	ActuatorsJPARepository actuatorsRepository;
	
	@Autowired
	private SensorRulesService sensorRulesService;
	
	@PostConstruct
	private void registerInstance() {
		instance = this;
	} 

	public static ActuatorsService getInstance() {
		return instance;
	}
	
	public Actuator newActuator(Actuator actuator)  {
		actuator = actuatorsRepository.saveAndFlush(actuator);
		this.setUpInterface(actuator);
		return actuator;	
	}
	
	public void setActuatorValue(PropertyActuatedEnum propertyActuated, String actuatorName, String value)  {
		assert(this.exist(actuatorName));
		Actuator actuator = this.getByName(actuatorName);
		new ActuatorSetValueService(actuator, propertyActuated, value).setValue();
		
	}
	
	public List<Actuator> getAllActuators() {
		return actuatorsRepository.findAll();
	}

	public List<Property> getSupportedPropertiesActuated() {
		List<Property> propertiesMeasured = new ArrayList<>();
		for (PropertyActuatedEnum propertyMeasured: PropertyActuatedEnum.ALL_INSTANCES) {
			propertiesMeasured.add(propertyMeasured);
		}
		return propertiesMeasured;
	}

	public boolean exist(String actuatorName) {
		return this.getByName(actuatorName) != null;
	}

	@Override
	public Actuator getByName(String name) {
		List<Actuator> actuators = actuatorsRepository.findByName(name);
		return actuators.isEmpty()? null : actuators.get(0) ;

	}


	public void deleteActuatorByName(String name){
		Actuator actuator = getByName(name);
		this.setDownInterface(actuator);
		sensorRulesService.deleteTriggerActuatorSensorRules(name);		
		actuatorsRepository.deleteById(actuator.getId());		
	}


	
	
	public boolean hasActuatorProperty(String actuatorName, PropertyActuatedEnum propertyActuated) {
		assert(exist(actuatorName));
		boolean hasActuatorProperty = true;
		Actuator actuator = getByName(actuatorName);
		if (propertyActuated == null || !actuator.getPropertiesActuated().contains(propertyActuated)) {
			hasActuatorProperty = false;
		}
		return hasActuatorProperty;
	}


	void update(String name, ActuatorValues actuatorValues) {
		Actuator actuator = this.getByName(name);
		assert(actuator!=null);
		actuator.setValues(actuatorValues);
		actuator.setActive(true);
		actuatorsRepository.saveAndFlush(actuator);
	}


}
