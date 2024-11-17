package com.iotassistant.services;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.iotassistant.models.CameraHttpInterface;
import com.iotassistant.models.devices.CameraInterfaceException;
import com.iotassistant.utils.HttpClient;

@Controller
public class HttpDevicesService {
	
	private HttpClient httpClient;
	
	private static HttpDevicesService instance;
	
	Logger logger = LoggerFactory.getLogger(HttpDevicesService.class);

	public HttpDevicesService() {
		super();
		this.httpClient = new HttpClient();
	}
	
	@PostConstruct
	private void registerInstance() {
		instance = this;
	} 
	
	public static HttpDevicesService getInstance() {
		return instance;
	}

	public byte[] getCameraPicture(CameraHttpInterface cameraHttpInterface) throws CameraInterfaceException {
		try {
			return this.httpClient.getForObject(cameraHttpInterface.getUrl(), byte[].class);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new CameraInterfaceException(e.getMessage());
		}
	}

}
