package com.iotassistant.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.models.devices.Camera;
import com.iotassistant.models.devices.CameraInterfaceException;
import com.iotassistant.models.devices.Device;
import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.models.devices.transductors.ActuatorValues;
import com.iotassistant.models.devices.transductors.SensorValues;
import com.iotassistant.models.devices.transductors.propertymeasured.PropertyMeasuredEnum;
import com.iotassistant.repositories.DevicesJPARepository;


@Component
public class DevicesFacadeService  {
	
	@Autowired
	private DevicesJPARepository devicesJPARepository;
	
	@Autowired
	private TransductorsFacadeService transductorsService;
	
	@Autowired
	private CamerasService camerasService;
	
	@Autowired
	private GpsesService gpsesService;
	
	private static DevicesFacadeService instance;
	
	@PostConstruct
	private void registerInstance() {
		instance = this;
	} 
	
	public static DevicesFacadeService getInstance() {
		return instance;
	}

	public List<Device> getAllDevices() {
		return devicesJPARepository.findAll();	
	}

	public void setUpInterface(Device device)  {
		 new DeviceSetUpInterfaceService().setUp(device.getInterface());
	}
	
	public void setDownInterface(Device device)  {
		 new DeviceSetDownInterfaceService().setDown(device.getInterface());
	}
	
	public byte[] getCameraPicture(Camera camera) throws CameraInterfaceException {
		return camerasService.getPicture(camera.getName());
		
	}
	
	public void updateSensorValues(String sensorName, SensorValues sensorValues) {
		transductorsService.updateSensorValues(sensorName, sensorValues);	
	}
	
	public void updateActuatorValues(String actuatorName, ActuatorValues actuatorValues) {
		transductorsService.updateActuatorValues(actuatorName, actuatorValues);
	}
	
	public void updateGpsPosition(String gpsName, GpsPosition position) {
		gpsesService.update(gpsName, position);
	}

	public boolean sensorHasProperty(String sensorName, PropertyMeasuredEnum propertyObserved) {
		return transductorsService.sensorHasProperty(sensorName, propertyObserved);
	}

	public boolean existSensor(String sensorName) {
		return transductorsService.existSensor(sensorName);
	}

	public boolean existGps(String gpsName) {
		return gpsesService.exist(gpsName);
	}

	public boolean existActuator(String actuatorName) {
		return transductorsService.existActuator(actuatorName);
	}

	public boolean existCamera(String cameraName) {
		return camerasService.exist(cameraName);
	}
	
	public Device getTTNDeviceByEndDeviceId (String deviceId) {
		return devicesJPARepository.findDeviceByEndDeviceId(deviceId);
	}

	public Device getHttpDeviceByUrl(String url) {
		return devicesJPARepository.findDeviceByUrl(url);
	}


}
