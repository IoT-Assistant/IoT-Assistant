package com.iotassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iotassistant.models.rules.CameraSensorRule;

public interface CameraSensorRuleJPARepository extends JpaRepository<CameraSensorRule, Integer>{

}

