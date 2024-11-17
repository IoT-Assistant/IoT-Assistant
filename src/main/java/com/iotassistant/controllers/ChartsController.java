package com.iotassistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iotassistant.controllers.dtos.ChartDTO;
import com.iotassistant.controllers.dtos.ChartsDTO;
import com.iotassistant.controllers.dtos.ErrorDTO;
import com.iotassistant.models.SensorChart;
import com.iotassistant.services.ChartsService;
import com.iotassistant.validators.ChartsValidator;
import com.iotassistant.validators.ValidationError;


@RestController
@RequestMapping("${charts.uri}")
public class ChartsController { 
	
	@Autowired
	private ChartsService chartsService;

	@Autowired
	private ChartsValidator chartsValidator;
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<?> newChart(@RequestBody ChartDTO chartDTO)  {
		SensorChart chart = chartDTO.getChart();
		ValidationError validationError = chartsValidator.validateNew(chart);
		if (validationError.hasErrors())  {
			return ErrorDTO.getInstance(validationError).getResponseEntity();
	    }
		chartsService.newChart(chart);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
	

    @RequestMapping(value="/", method = RequestMethod.GET)
	public ChartsDTO getCharts() throws Exception {
		return new ChartsDTO(chartsService.getAllCharts());
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteChartById(@PathVariable("id") int id)  {
		ValidationError validationError = chartsValidator.validateDelete(id);
		if (validationError.hasErrors())  {
			return ErrorDTO.getInstance(validationError).getResponseEntity();
	    }
		chartsService.deleteChartById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);			
	}


}
