package com.shopsphere.order.service;

import com.shopsphere.order.client.*;
import com.shopsphere.order.dto.*;
import com.shopsphere.order.entity.Order;
import com.shopsphere.order.entity.OrderStatus;
import com.shopsphere.order.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductClient productClient;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;

    public OrderService(
            OrderRepository orderRepository,
            ProductClient productClient,
            InventoryClient inventoryClient,
            PaymentClient paymentClient,
            NotificationClient notificationClient) {

        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
        this.notificationClient = notificationClient;
    }

    @Transactional
    public OrderResponse createOrder(  
            CreateOrderRequest request) {

        // 1. Product Service se product information
        ProductResponse product =
                productClient.getProduct(
                        request.getProductId());

        if (product == null) {
            throw new RuntimeException(
                    "Product not found");
        }

        // 2. Calculate amount
        BigDecimal total =
                product.getPrice()
                        .multiply(
                            BigDecimal.valueOf(
                                request.getQuantity()));

        // 3. Inventory reserve
        inventoryClient.reserve(
                request.getProductId(),
                request.getQuantity());

        // 4. Create order
        Order order = new Order();

        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.CREATED);

        Order saved =
                orderRepository.save(order);

        try {

            // 5. Payment
            PaymentResponse payment =
                    paymentClient.pay(
                            saved.getId(),
                            total);

            if (payment == null ||
                    !"SUCCESS".equals(
                            payment.getStatus())) {

                inventoryClient.release(
                        request.getProductId(),
                        request.getQuantity());

                saved.setStatus(
                        OrderStatus.CANCELLED);

                orderRepository.save(saved);

                throw new RuntimeException(
                        "Payment failed");
            }

            // 6. Confirm order
            saved.setStatus(
                    OrderStatus.CONFIRMED);

            saved =
                    orderRepository.save(saved);

            // 7. Notification
            notificationClient.notifyOrder(
                    saved.getId(),
                    saved.getUserId());

            return toResponse(saved);

        } catch (RuntimeException ex) {

            if (saved.getStatus()
                    != OrderStatus.CANCELLED) {

                inventoryClient.release(
                        request.getProductId(),
                        request.getQuantity());

                saved.setStatus(
                        OrderStatus.CANCELLED);

                orderRepository.save(saved);
            }

            throw ex;
        }
    }

    public OrderResponse getOrder(Long id) {

        Order order =
                orderRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found"));

        return toResponse(order);
    }

    private OrderResponse toResponse(
            Order order) {

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getStatus().name());
    }
}