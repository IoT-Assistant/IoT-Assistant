package com.iotassistant.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.SensorChart;
import com.iotassistant.services.ChartsService;
import com.iotassistant.services.SensorsService;

@Component
public class ChartsValidator{
	
	@Autowired
	private ChartsService chartsService;

	@Autowired
	private SensorsService sensorsService;
	
	
	public ValidationError validateNew(SensorChart chart) {
		ValidationError error = ValidationError.NO_ERROR;
		if (chartsService.existChart(chart)) {
			error = ValidationError.ENTITY_ALREADY_EXIST;
			error.formatMsg("Chart");
		}
		if (!sensorsService.exist(chart.getSensorName()))  {
			error = ValidationError.ENTITY_NOT_FOUND;
			error.formatMsg("Sensor " + chart.getSensorName());
	    }
		if (!sensorsService.sensorHasProperty(chart.getSensorName(), chart.getPropertyObserved()))  {
			error = ValidationError.ENTITY_HAS_NOT_PROPERTY;
			error.formatMsg("Sensor " + chart.getSensorName());
	    }
		return error;
	}


	public ValidationError validateDelete(int id) {
		ValidationError error = ValidationError.NO_ERROR;
		if(chartsService.getChartById(id) == null) {
			error = ValidationError.ENTITY_NOT_FOUND;
        }
		error.formatMsg("Chart");
		return error;
	}

}
