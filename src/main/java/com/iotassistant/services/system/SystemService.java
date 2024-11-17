package com.iotassistant.services.system;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.iotassistant.models.ServerStatus;
import com.iotassistant.models.SystemSettings;
import com.iotassistant.models.TelegramBotManager;
import com.iotassistant.models.devices.Device;
import com.iotassistant.models.devices.transductors.Property;
import com.iotassistant.mqtt.MqttDevicesController;
import com.iotassistant.services.DevicesFacadeService;
import com.iotassistant.ttn.TTNDevicesController;
import com.iotassistant.utils.Date;

@Service
public class SystemService implements CommandLineRunner{
	
	@Autowired
	SystemCapabilitiesService systemCapabilitiesService;
	
	@Autowired
	SystemSettingsService systemSettingsService;
	
	@Autowired
	ServersStatusService serversStatusService;
	
	@Autowired
	private DevicesFacadeService devicesService;
	

	public List<Property> getSupportedSensorProperties() {
		return systemCapabilitiesService.getSupportedSensorProperties();
	}

	public List<String> getSupportedSensorInterfaces() {
		return this.systemCapabilitiesService.getSupportedSensorInterfaces();
	}

	public List<Property> getSupportedActuatorProperties() {
		return systemCapabilitiesService.getSupportedActuatorProperties();
	}

	public List<String> getSupportedActuatorInterfaces() {
		return systemCapabilitiesService.getSupportedActuatorInterfaces();
	}

	public List<String> getSupportedNotificationTypes() {
		return systemCapabilitiesService.getSupportedNotificationTypes();
	}
	
	public List<String> getSupportedSensorRuleTriggerIntervalEnum() {
		return systemCapabilitiesService.getSupportedSensorRuleTriggerIntervalEnum();
	}
	

	public List<String> getSupportedChartTypes() {
		return systemCapabilitiesService.getSupportedChartTypes();
	}

	public List<String> getSupportedChartIntervals() {
		return systemCapabilitiesService.getSupportedChartIntervals();
	}

	public List<String> getSupportedSampleIntervals() {
		return systemCapabilitiesService.getSupportedSampleIntervals();
	}

	public List<String> getSupportedTransductorsWatchdogIntervals() {
		return systemCapabilitiesService.getSupportedTransductorsWatchdogIntervals();
	}
	
	public List<String> getSupportedCamerasWatchdogIntervals() {
		return systemCapabilitiesService.getSupportedCamerasWatchdogIntervals();
	}

	public void powerOff() throws SystemCantShutdownException{
		String shutdownCommand = "shutdown.exe -s -t 0";
	    String operatingSystem = System.getProperty("os.name");
	    if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
	        shutdownCommand = "/sbin/shutdown -h now";
	    }    
		try {
			System.out.println("Shutting down the system after 5 seconds.");
			Runtime.getRuntime().exec(shutdownCommand);
		    System.exit(0);
		}
		catch(IOException e) {
			throw new SystemCantShutdownException(e.getMessage());
		}
	}
	

	public List<String> getSupportedSensorRulesTypes() {
		return systemCapabilitiesService.getSupportedSensorRulesTypes();
	}


	public List<String> getSupportedRulesTriggerInterval() {
		return systemCapabilitiesService.getSupportedRulesTriggerInterval();
	}

	public List<String> getSupportedCameraInterfaces() {
		return systemCapabilitiesService.getSupportedCameraInterfaces();
	}

	public List<String> getSupportedGpsesInterfaces() {
		return systemCapabilitiesService.getSupportedGpsesInterfaces();
	}

	public List<String> getSupportedGpsesWatchdogIntervals() {
		return systemCapabilitiesService.getSupportedGpsesWatchdogIntervals();
	}
	
	public List<ServerStatus> getServersStatus() {
		return serversStatusService.getServersStatus();
	}

	public String getUptime() {
		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		return Date.getTimeFromUptime(rb.getUptime());
	}

	public List<String> getSupportedGpsRulesTypes() {
		return systemCapabilitiesService.getSupportedGpsRulesTypes();
	}

	public SystemSettings getSettings() {
		return systemSettingsService.getSettings();
	}

	public void setSettings(SystemSettings newSettings) {
		 systemSettingsService.setSettings(newSettings);
		 TTNDevicesController.getInstance().connect(systemSettingsService);
		 MqttDevicesController.getInstance().connect(systemSettingsService);
		 TelegramBotManager.getInstance().connect(systemSettingsService);
		 setDownDevicesInterfaces();
		 setUpDevicesInterfaces();
	}
	
	private void setDownDevicesInterfaces()  {
		List<Device> allDevices = devicesService.getAllDevices();
        for (Device device : allDevices) {
        		devicesService.setDownInterface(device);
        }	
	}

	@Override
	public void run(String... args) throws Exception {
		setUpDevicesInterfaces();
	}
	
	private void setUpDevicesInterfaces()  {
		List<Device> allDevices = devicesService.getAllDevices();
        for (Device device : allDevices) {
        		devicesService.setUpInterface(device);
        }	
	}

}
