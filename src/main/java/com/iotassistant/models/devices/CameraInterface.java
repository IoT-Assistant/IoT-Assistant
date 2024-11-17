package com.iotassistant.models.devices;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="cameraInterface_type")
@Table(name="cameraInterface")
@DiscriminatorValue("cameraInterface")
public abstract class CameraInterface extends DeviceInterface{

	
	public abstract void accept(CameraInterfaceVisitor cameraInterfaceVisitor) throws CameraInterfaceException;
	
}
