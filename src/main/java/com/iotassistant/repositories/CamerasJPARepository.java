package com.iotassistant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iotassistant.models.devices.Camera;

public interface CamerasJPARepository  extends JpaRepository<Camera, Integer>{
	
	List<Camera> findByName(String name);

}
