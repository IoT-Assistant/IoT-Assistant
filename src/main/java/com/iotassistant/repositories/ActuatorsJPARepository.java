package com.iotassistant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iotassistant.models.devices.transductors.Actuator;

public interface ActuatorsJPARepository extends JpaRepository<Actuator, Integer>{

	List<Actuator> findByName(String name);
}
