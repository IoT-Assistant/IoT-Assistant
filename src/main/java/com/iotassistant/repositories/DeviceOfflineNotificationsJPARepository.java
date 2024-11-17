package com.iotassistant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotassistant.models.notifications.DeviceOfflineNotification;

@Repository
interface DeviceOfflineNotificationsJPARepository extends JpaRepository<DeviceOfflineNotification, Integer>{


	List<DeviceOfflineNotification> findAllByOrderByIdDesc();

}
