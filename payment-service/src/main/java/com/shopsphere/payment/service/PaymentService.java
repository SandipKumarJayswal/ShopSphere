package com.shopsphere.payment.service;

import com.shopsphere.payment.entity.Payment;
import com.shopsphere.payment.entity.PaymentStatus;
import com.shopsphere.payment.repository.PaymentRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(
            PaymentRepository repository) {

        this.repository = repository;
    }

    public Payment makePayment(
            Long orderId,
            BigDecimal amount) {

        Payment payment = new Payment();

        payment.setOrderId(orderId);
        payment.setAmount(amount);

        // Learning project:
        // assume payment is successful.
        payment.setStatus(PaymentStatus.SUCCESS);

        return repository.save(payment);
    }
}