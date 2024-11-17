package com.iotassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotassistant.models.rules.Rule;

@Repository
public interface RulesJPARepository  extends JpaRepository<Rule, Integer>{

}
