package com.iotassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotassistant.models.rules.GpsRule;

@Repository
public interface GpsRuleJPARepository  extends JpaRepository<GpsRule, Integer>{

}
