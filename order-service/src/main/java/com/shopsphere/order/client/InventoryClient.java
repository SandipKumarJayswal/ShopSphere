package com.shopsphere.order.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class InventoryClient {

    private final RestClient restClient;

    public InventoryClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8085").build();
    }

    public void reserve(Long productId, Integer quantity) {
        restClient.post()
                .uri("/api/inventory/{productId}/reserve/{quantity}", productId, quantity)
                .retrieve()
                .toBodilessEntity();
    }

    public void release(Long productId, Integer quantity) {
        restClient.post()
                .uri("/api/inventory/{productId}/release/{quantity}", productId, quantity)
                .retrieve()
                .toBodilessEntity();
    }
}