package com.iotassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iotassistant.models.devices.Device;

@Repository
public interface DevicesJPARepository extends JpaRepository<Device, Integer>{
	
	@Query("select d from Device d where d.deviceInterface.deviceId =:deviceId")
	Device findDeviceByEndDeviceId(String deviceId);

	@Query("select d from Device d where d.deviceInterface.url =:url")
	Device findDeviceByUrl(String url);
	

}
