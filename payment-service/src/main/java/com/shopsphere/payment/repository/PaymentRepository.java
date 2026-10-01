package com.shopsphere.payment.repository;

import com.shopsphere.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
        
}