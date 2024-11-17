package com.iotassistant.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iotassistant.controllers.dtos.ErrorDTO;
import com.iotassistant.controllers.dtos.devices.EnableDTO;
import com.iotassistant.controllers.dtos.devices.transductors.NewMqttInterfaceSensorDTO;
import com.iotassistant.controllers.dtos.devices.transductors.NewTTNInterfaceSensorDTO;
import com.iotassistant.controllers.dtos.devices.transductors.SensorDTO;
import com.iotassistant.controllers.dtos.devices.transductors.SensorsDTO;
import com.iotassistant.models.devices.transductors.Sensor;
import com.iotassistant.services.SensorsService;
import com.iotassistant.validators.SensorsValidator;
import com.iotassistant.validators.ValidationError;

@RestController
@RequestMapping("${sensors.uri}")
public class SensorsController {
	
	@Autowired
	private SensorsService sensorsService;
	
	@Autowired
	private SensorsValidator sensorsValidator;
		
	@RequestMapping(value="/", method = RequestMethod.GET)
	public SensorsDTO getAllSensors() {
		return new SensorsDTO(sensorsService.getAllSensors());		
			
	}
	
	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getSensorByName(@PathVariable("name") String name) {
		ValidationError validationError = sensorsValidator.validateGet(name);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
		return new ResponseEntity<>(new SensorDTO(sensorsService.getByName(name)), HttpStatus.OK);
		
	}	
	
	

	@RequestMapping(value="/mqtt-interface-sensors/", method = RequestMethod.POST)
	public ResponseEntity<?> newMqttInterfaceSensor(@RequestBody @Valid NewMqttInterfaceSensorDTO newMqttInterfaceSensorDTO){
		Sensor sensor = newMqttInterfaceSensorDTO.getSensor();
		ValidationError validationError = sensorsValidator.validateNew(sensor);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    sensorsService.newSensor(sensor);
	    return new ResponseEntity<>(null, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/ttn-interface-sensors/", method = RequestMethod.POST)
	public ResponseEntity<?> newTTNInterfaceSensor(@RequestBody @Valid NewTTNInterfaceSensorDTO newTTNInterfaceSensorDTO){
		Sensor sensor = newTTNInterfaceSensorDTO.getSensor();
		ValidationError validationError = sensorsValidator.validateNew(sensor);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    sensorsService.newSensor(sensor);
	    return new ResponseEntity<>(null, HttpStatus.CREATED);	
	}
	

	@RequestMapping(value="/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteSensor(@PathVariable("name") String name) {
		ValidationError validationError = sensorsValidator.validateDelete(name);
		if (validationError.hasErrors())  {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
	    }
	    sensorsService.deleteByName(name);
	    return new ResponseEntity<>(null, HttpStatus.OK);	
	}
	

	@RequestMapping(value="/{name}", method = RequestMethod.PATCH)
	public ResponseEntity<?> enableSensorWatchdog(@PathVariable("name") String sensorName, @RequestBody EnableDTO enableWatchdogDTO)  {
		ValidationError validationError = sensorsValidator.validateEnableDisableWatchdog(sensorName);
		if (validationError.hasErrors())  {
			return ErrorDTO.getInstance(validationError).getResponseEntity();
	    }
	    sensorsService.enableDisableWatchdog(enableWatchdogDTO.isEnable(), sensorName);
	    return new ResponseEntity<>(null, HttpStatus.OK);
	}


}
