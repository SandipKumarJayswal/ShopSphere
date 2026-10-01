package com.shopsphere.notification.controller;

import com.shopsphere.notification.service.NotificationService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(
            NotificationService service) {

        this.service = service;
    }

    @PostMapping("/order")
    public String sendOrderNotification(
            @RequestParam Long orderId,
            @RequestParam Long userId) {

        return service.sendOrderNotification(
                orderId,
                userId);
    }
}