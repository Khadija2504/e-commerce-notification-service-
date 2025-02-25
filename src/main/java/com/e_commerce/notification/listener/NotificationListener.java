package com.e_commerce.notification.listener;

import com.e_commerce.notification.model.NotificationEvent;
import com.e_commerce.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;

    @RabbitListener(queues = "notificationQueue")
    public void handleNotification(NotificationEvent event) {
        emailService.sendNotification(event);
    }
}
