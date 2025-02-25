package com.e_commerce.notification.service;

import com.e_commerce.notification.model.NotificationEvent;

public interface EmailService {
    String generateMessage(NotificationEvent event);
    void sendNotification(NotificationEvent event);
}
