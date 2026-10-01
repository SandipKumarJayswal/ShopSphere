package com.shopsphere.order.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationClient {

    private final RestClient restClient;

    public NotificationClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://localhost:8086")
                .build();
    }

    public void notifyOrder(
            Long orderId,
            Long userId) {

        restClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                            .path("/api/notifications/order")
                            .queryParam("orderId", orderId)
                            .queryParam("userId", userId)
                            .build())
                .retrieve()
                .toBodilessEntity();
    }
}