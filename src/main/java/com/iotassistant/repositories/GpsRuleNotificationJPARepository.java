package com.iotassistant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iotassistant.models.notifications.GpsRuleNotification;

public interface GpsRuleNotificationJPARepository extends JpaRepository<GpsRuleNotification, Integer>{


	List<GpsRuleNotification> findAllByOrderByIdDesc();

}