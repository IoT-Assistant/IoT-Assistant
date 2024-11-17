package com.iotassistant.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotassistant.models.devices.transductors.Sensor;

@Repository
public interface SensorsJPARepository extends JpaRepository<Sensor, Integer>{

	List<Sensor> findByName(String name);


}

