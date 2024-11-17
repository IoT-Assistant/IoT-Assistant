package com.iotassistant.ttn;

import java.io.IOException;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iotassistant.models.devices.Camera;
import com.iotassistant.models.devices.Device;
import com.iotassistant.models.devices.DeviceVisitor;
import com.iotassistant.models.devices.Gps;
import com.iotassistant.models.devices.transductors.Actuator;
import com.iotassistant.models.devices.transductors.Sensor;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.services.DevicesFacadeService;
import com.iotassistant.utils.JSONParser;

public class TTNArrivedMessageService implements DeviceVisitor {
	
	Logger logger = LoggerFactory.getLogger(TTNArrivedMessageService.class);
	
	private String topic;
	
	private MqttMessage message;
	
	private DevicesFacadeService devicesService;

	public TTNArrivedMessageService(String topic, MqttMessage message) {
		this.topic = topic;
		this.message = message;
		this.devicesService = DevicesFacadeService.getInstance();
	}

	public void updateDevice() {
		String deviceId = TTNDeviceInterface.getDeviceId(topic);
		Device device = DevicesFacadeService.getInstance().getTTNDeviceByEndDeviceId(deviceId);
		device.accept(this);
		
	}

	@Override
	public void visit(Sensor sensor) {
		try {
			TTNSensorValuesDTO sensorValuesDTO = new JSONParser().parseJsonBodyAs(TTNSensorValuesDTO.class, message.toString());
			List<PropertyMeasuredEnum> sensorProperties = sensor.getPropertiesMeasured();
			if (sensorValuesDTO.hasErrors(sensorProperties)) {
				this.logError(sensor, sensorValuesDTO.getErrors());
			}
			else {
				devicesService.updateSensorValues(sensor.getName(), sensorValuesDTO.getSensorValues());
			}	
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	private void logError(Device device, List<String> errors) {
		logger.error(device.getName() + ": " + errors.toString());
	}

	@Override
	public void visit(Actuator actuator) {} // Not supported yet

	@Override
	public void visit(Camera camera) {} // Not supported (does it make sense Camera over LoRa?)

	@Override
	public void visit(Gps gps) {
		try {
			TTNGpsPositionDTO gpsPositionDTO = new JSONParser().parseJsonBodyAs(TTNGpsPositionDTO.class, message.toString());
			if (gpsPositionDTO.hasErrors()) {
				this.logError(gps, gpsPositionDTO.getErrors());			
			} 
			else {
				devicesService.updateGpsPosition(gps.getName(), gpsPositionDTO.getPosition());
			}	
		}
		catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}

}
