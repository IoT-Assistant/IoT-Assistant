package com.iotassistant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotassistant.models.devices.Gps;

@Repository
public interface GpsJPARepository  extends JpaRepository<Gps, Integer>{
	
	List<Gps> findByName(String name);

}
