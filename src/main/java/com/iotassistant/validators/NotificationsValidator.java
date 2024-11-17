package com.iotassistant.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iotassistant.services.NotificationsService;

@Component
public class NotificationsValidator {
	
	private @Autowired
	NotificationsService notificationsService;

	public ValidationError validateDelete(int id) {
		return this.validateExist(id);
	}
	
	public ValidationError validateExist(int id) {
		ValidationError error = ValidationError.NO_ERROR;
		if (notificationsService.getNotificationById(id) == null) {
			error = ValidationError.ENTITY_NOT_FOUND;
			error.formatMsg("Notification " + id);
        }	
		return error;
	}

	public ValidationError validateGet(int id) {
		return this.validateExist(id);
	}

}
