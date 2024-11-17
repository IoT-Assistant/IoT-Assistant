package com.iotassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotassistant.models.rules.TriggerActuatorSensorRule;

@Repository
public interface TriggerActuatorSensorRuleJPARepository extends JpaRepository<TriggerActuatorSensorRule, Integer>{

}

