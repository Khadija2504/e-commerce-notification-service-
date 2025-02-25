package com.e_commerce.notification.service.serviceImpl;

import com.e_commerce.notification.model.NotificationEvent;
import com.e_commerce.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Value("${notification.email.from}")
    private String fromEmail;

    @Override
    public void sendNotification(NotificationEvent event) {
        String subject = "Order Update: " + event.getStatus();
        String message = generateMessage(event);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(fromEmail);
        email.setTo(event.getEmail());
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }

    @Override
    public String generateMessage(NotificationEvent event) {
        switch (event.getStatus()) {
            case "ORDER_CREATED":
                return "Your order " + event.getOrderId() + " has been created successfully!";
            case "PAYMENT_SUCCESS":
                return "Payment received for your order " + event.getOrderId() + ". Thank you!";
            case "ORDER_SHIPPED":
                return "Your order " + event.getOrderId() + " has been shipped!";
            default:
                return "Update on your order " + event.getOrderId();
        }
    }
}
