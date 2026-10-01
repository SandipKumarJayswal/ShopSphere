package com.shopsphere.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public String sendOrderNotification(
            Long orderId,
            Long userId) {

        String message =
                "Notification sent for order "
                + orderId
                + " to user "
                + userId;

        System.out.println(message);

        return message;
    }
}