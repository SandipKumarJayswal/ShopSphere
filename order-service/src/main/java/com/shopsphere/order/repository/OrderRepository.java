package com.shopsphere.order.repository;

import com.shopsphere.order.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long> {
}