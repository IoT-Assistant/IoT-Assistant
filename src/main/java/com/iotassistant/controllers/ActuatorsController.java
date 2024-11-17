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
import com.iotassistant.controllers.dtos.devices.transductors.ActuatorNewValueDTO;
import com.iotassistant.controllers.dtos.devices.transductors.ActuatorsDTO;
import com.iotassistant.controllers.dtos.devices.transductors.NewMqttInterfaceActuatorDTO;
import com.iotassistant.models.devices.transductors.Actuator;
import com.iotassistant.models.devices.transductors.propertyactuated.PropertyActuatedEnum;
import com.iotassistant.services.ActuatorsService;
import com.iotassistant.validators.ActuatorsValidator;
import com.iotassistant.validators.ValidationError;

@RestController
@RequestMapping("${actuators.uri}")
public class ActuatorsController {
	
	@Autowired
	private ActuatorsService actuatorsService;
	
	@Autowired
	private ActuatorsValidator actuatorsValidator;
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllActuators() {
		ActuatorsDTO actuatorsDTO =  new ActuatorsDTO(actuatorsService.getAllActuators());
		return new ResponseEntity<>(actuatorsDTO, HttpStatus.OK);
	
	}

	
	@RequestMapping(value="/mqtt-interface-actuators/", method = RequestMethod.POST)
	public ResponseEntity<?> newMqttInterfaceActuator(@RequestBody @Valid NewMqttInterfaceActuatorDTO newMqttInterfaceActuatorDTO)  {
		Actuator actuator = newMqttInterfaceActuatorDTO.getActuator();
		ValidationError validationError = actuatorsValidator.validateNew(actuator);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
		actuatorsService.newActuator(actuator);
	    return new ResponseEntity<>(null, HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(value="/{name}", method = RequestMethod.PATCH)
	public ResponseEntity<?> setActuatorValue(@PathVariable("name") String actuatorName, @RequestBody ActuatorNewValueDTO actuatorValueDTO) {
		String value = actuatorValueDTO.getValue();
		PropertyActuatedEnum propertyActuated = actuatorValueDTO.getPropertyActuated();
		ValidationError validationError = actuatorsValidator.validateSetValue(actuatorName, value, propertyActuated);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
		actuatorsService.setActuatorValue(propertyActuated, actuatorName, value);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteActuator(@PathVariable("name") String name) {
		ValidationError validationError = actuatorsValidator.validateDelete(name);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    actuatorsService.deleteActuatorByName(name);
		return new ResponseEntity<>(null, HttpStatus.OK);

	}
	
	
	@RequestMapping(value="/{name}/watchdog", method = RequestMethod.PATCH)
	public ResponseEntity<?> enableActuatorWatchdog(@PathVariable("name") String actuatorName, @RequestBody EnableDTO enableWatchdogDTO)   {
		ValidationError validationError = actuatorsValidator.validateEnableDisableWatchdog(actuatorName);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    actuatorsService.enableDisableWatchdog(enableWatchdogDTO.isEnable(), actuatorName);
	    return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
