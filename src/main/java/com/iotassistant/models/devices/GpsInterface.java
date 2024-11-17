package com.iotassistant.models.devices;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="gpsInterface_type")
@DiscriminatorValue("gpsInterface")
@Table(name="gpsInterface")
public abstract class GpsInterface extends DeviceInterface{
	

}
