package com.shopsphere.order.client;

import com.shopsphere.order.dto.PaymentResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Component
public class PaymentClient {

    private final RestClient restClient;

    public PaymentClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://localhost:8084")
                .build();
    }

    public PaymentResponse pay(
            Long orderId,
            BigDecimal amount) {

        return restClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                            .path("/api/payments")
                            .queryParam("orderId", orderId)
                            .queryParam("amount", amount)
                            .build())
                .retrieve()
                .body(PaymentResponse.class);
    }
}