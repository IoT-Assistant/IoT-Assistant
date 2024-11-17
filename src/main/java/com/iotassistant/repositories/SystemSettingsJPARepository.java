package com.iotassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iotassistant.models.SystemSettings;

public interface SystemSettingsJPARepository extends JpaRepository<SystemSettings, Integer>{

	SystemSettings findFirstByOrderByIdDesc();



}