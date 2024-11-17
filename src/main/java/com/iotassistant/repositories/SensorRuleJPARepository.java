package com.iotassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotassistant.models.rules.SensorRule;

@Repository
public interface SensorRuleJPARepository extends JpaRepository<SensorRule, Integer>{

}
