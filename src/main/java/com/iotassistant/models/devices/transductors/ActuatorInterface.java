package com.iotassistant.models.devices.transductors;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.iotassistant.models.devices.DeviceInterface;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="actuatorInterface_type")
@DiscriminatorValue("actuatorInterface")
@Table(name="actuatorInterface")
public abstract class ActuatorInterface  extends DeviceInterface{
	

	public abstract void accept(ActuatorInterfaceVisitor actuatorInterfaceVisitor);
	

}
