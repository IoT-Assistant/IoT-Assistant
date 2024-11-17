package com.iotassistant.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotassistant.models.devices.Gps;
import com.iotassistant.models.devices.GpsInterfaceTypeEnum;
import com.iotassistant.models.devices.GpsPosition;
import com.iotassistant.models.devices.WatchdogInterval;
import com.iotassistant.repositories.GpsJPARepository;

@Service
public class GpsesService extends DeviceService{
	
	private static GpsesService instance;
	
	@Autowired
	private GpsJPARepository gpsesRepository;
	
	@Autowired
	private GpsRulesService gpsRulesService;
	
	@PostConstruct
	private void registerInstance() {
		instance = this;
	} 

	public static GpsesService getInstance() {
		return instance;
	}

	public void setUpInterface(Gps gps) {
		new DeviceSetUpInterfaceService().setUp(gps.getInterface());		
	}

	public void update(String gpsName, GpsPosition position) {
		Gps gps = this.getByName(gpsName);
		assert(gps!=null);
		gps.setPosition(position);
		gps.setActive(true);
		gpsesRepository.saveAndFlush(gps);
		gpsRulesService.applyRules(gps.getName(), position);
		
	}

	@Override
	public Gps getByName(String name)  {
		List<Gps> gpses =  gpsesRepository.findByName(name);
		return gpses.isEmpty() ? null : gpses.get(0);
	}

	public List<String> getSupportedInterfaces() {
		return GpsInterfaceTypeEnum.getAllInstances();
	}

	public List<String> getSupportedWatchdogIntervals() {
		return WatchdogInterval.getAvailableWatchdogIntervalOptions();
	}

	public List<Gps> getAllGpses() {
		return gpsesRepository.findAll();
	}

	public boolean exist(String name) {
		return getByName(name) != null;
	}

	public void newGps(Gps gps) {
		gpsesRepository.saveAndFlush(gps);
		this.setUpInterface(gps);
	}

	public void deleteGpsByName(String name) {
		Gps gps = this.getByName(name);
		this.setDownInterface(gps);
		gpsRulesService.deleteGpsRules(name);
		gpsesRepository.deleteById(gps.getId());
	}

}
