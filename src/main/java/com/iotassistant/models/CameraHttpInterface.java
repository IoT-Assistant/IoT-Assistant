package com.iotassistant.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iotassistant.models.devices.CameraInterface;
import com.iotassistant.models.devices.CameraInterfaceException;
import com.iotassistant.models.devices.CameraInterfaceVisitor;
import com.iotassistant.models.devices.DeviceInterfaceVisitor;

@Entity
@DiscriminatorValue("cameraHTTPInterface")
public class CameraHttpInterface extends CameraInterface implements HttpDeviceInterface{

	@Column(unique=true)
	private String url;

	public CameraHttpInterface() {
		super();	
	}


	public CameraHttpInterface(String url) {
		this();
		this.url = url;
	}

	@Override
	public String getUrl() {
		return url;
	}


	@Override
	public void accept(CameraInterfaceVisitor cameraInterfaceVisitor) throws CameraInterfaceException {
		cameraInterfaceVisitor.visit(this);
		
	}


	@Override
	public void accept(DeviceInterfaceVisitor deviceInterfaceVisitor) {
		deviceInterfaceVisitor.visit(this);
		
	}
	
	
}
