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
import com.iotassistant.controllers.dtos.devices.GpsDTO;
import com.iotassistant.controllers.dtos.devices.GpsesDTO;
import com.iotassistant.controllers.dtos.devices.NewMqttInterfaceGpsDTO;
import com.iotassistant.controllers.dtos.devices.NewTTNInterfaceGpsDTO;
import com.iotassistant.models.devices.Gps;
import com.iotassistant.services.GpsesService;
import com.iotassistant.validators.GpsesValidator;
import com.iotassistant.validators.ValidationError;

@RestController
@RequestMapping("${gpses.uri}")
public class GpsesController{
	
	@Autowired
	private GpsesService gpsesService;
	
	@Autowired
	private GpsesValidator gpsesValidator;
		
	@RequestMapping(value="/", method = RequestMethod.GET)
	public GpsesDTO getAllGpses() {
		return new GpsesDTO(gpsesService.getAllGpses());		
	}
	
	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getGpsByName(@PathVariable("name") String name) {
		ValidationError validationError = gpsesValidator.validateGet(name);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
		return new ResponseEntity<>(new GpsDTO(gpsesService.getByName(name)), HttpStatus.OK);
		
	}	

	@RequestMapping(value="/mqtt-interface-gpses/", method = RequestMethod.POST)
	public ResponseEntity<?> newMqttInterfaceGps(@RequestBody @Valid NewMqttInterfaceGpsDTO newMqttGpsDTO){
		Gps gps = newMqttGpsDTO.getGps();
		ValidationError validationError = gpsesValidator.validateNew(gps);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    gpsesService.newGps(gps);
	    return new ResponseEntity<>(null, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/ttn-interface-gpses/", method = RequestMethod.POST)
	public ResponseEntity<?> newTTNInterfaceGps(@RequestBody @Valid NewTTNInterfaceGpsDTO newTTNGpsDTO){
		Gps gps = newTTNGpsDTO.getGps();
		ValidationError validationError = gpsesValidator.validateNew(gps);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    gpsesService.newGps(gps);
	    return new ResponseEntity<>(null, HttpStatus.CREATED);	
	}
	

	@RequestMapping(value="/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGps(@PathVariable("name") String name) {
		ValidationError validationError = gpsesValidator.validateDelete(name);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    gpsesService.deleteGpsByName(name);
	    return new ResponseEntity<>(null, HttpStatus.OK);	
	}
	

	@RequestMapping(value="/{name}", method = RequestMethod.PATCH)
	public ResponseEntity<?> enableGpsWatchdog(@PathVariable("name") String gpsName, @RequestBody EnableDTO enableWatchdogDTO)  {
		ValidationError validationError = gpsesValidator.validateEnableDisableWatchdog(gpsName);
		if (validationError.hasErrors()) {
			return  ErrorDTO.getInstance(validationError).getResponseEntity();
		}
	    gpsesService.enableDisableWatchdog(enableWatchdogDTO.isEnable(), gpsName);
	    return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
