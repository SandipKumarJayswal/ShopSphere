package com.shopsphere.payment.controller;

import com.shopsphere.payment.entity.Payment;
import com.shopsphere.payment.service.PaymentService;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(
            PaymentService service) {

        this.service = service;
    }

    @PostMapping
    public Payment makePayment(
            @RequestParam Long orderId,
            @RequestParam BigDecimal amount) {

        return service.makePayment(orderId, amount);
    }
}