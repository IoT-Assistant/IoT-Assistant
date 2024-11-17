package com.iotassistant.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.devices.transductors.Property;
import com.iotassistant.models.devices.transductors.Sensor;
import com.iotassistant.models.devices.transductors.SensorValues;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.repositories.SensorsJPARepository;

@Service
public class SensorsService extends DeviceService {
	
	private static SensorsService instance;
	
	@Autowired
	private SensorsJPARepository sensorsRepository;
	
	@Autowired
	private ChartsService chartsService;
	
	@Autowired
	private SensorRulesService sensorRulesService;
	
	@PostConstruct
	private void registerInstance() {
		instance = this;
	} 

	public static SensorsService getInstance() {
		return instance;
	}
	
	@Override
	public Sensor getByName(String name) {
		List<Sensor> sensors = sensorsRepository.findByName(name);
		return sensors.isEmpty() ? null : sensors.get(0);
	}

	public List<Sensor> getAllSensors() {
		return sensorsRepository.findAll();
	}

	public Sensor newSensor(Sensor sensor) {
		sensor = sensorsRepository.saveAndFlush(sensor);
		this.setUpInterface(sensor);
		chartsService.newChart(sensor);
		return sensor;
	}
	
	
	public List<Property> getSupportedPropertiesMeasured() {
		List<Property> propertiesMeasured = new ArrayList<>();
		for (PropertyMeasuredEnum propertyMeasured: PropertyMeasuredEnum.ALL_INSTANCES) {
			propertiesMeasured.add(propertyMeasured);
		}
		return propertiesMeasured;
	}

	public void deleteByName(String name)  {
		Sensor sensor = getByName(name);
		this.setDownInterface(sensor);
		chartsService.deleteChartsBySensorName(name);	
		sensorRulesService.deleteSensorRuleBySensorName(name);
		sensorsRepository.deleteById(sensor.getId());		
	}


	public boolean exist(String sensorName) {
		return this.getByName(sensorName) != null;
	}
	
	public boolean sensorHasProperty(String sensorName, PropertyMeasuredEnum propertyObserved) {
		assert(exist(sensorName));
		boolean hasSensorProperty = true;
		Sensor sensor = getByName(sensorName);
		if (!sensor.getPropertiesMeasured().contains(propertyObserved)) {
			hasSensorProperty = false;
		}
		return hasSensorProperty;
	}


	void update(String name, SensorValues values) {
		Sensor sensor = this.getByName(name);
		assert(sensor!=null);
		sensor.setValues(values);
		sensor.setActive(true);
		sensorsRepository.saveAndFlush(sensor);
		sensorRulesService.applyRules(sensor.getName(), values);
		chartsService.updateCharts(sensor);
		
	}


}
