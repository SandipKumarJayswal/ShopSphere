package com.shopsphere.order.client;

import com.shopsphere.order.dto.ProductResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductClient {

    private final RestClient restClient;

    public ProductClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://localhost:8082")
                .build();
    }

    public ProductResponse getProduct(Long productId) {

        return restClient.get()
                .uri("/api/products/{id}", productId)
                .retrieve()
                .body(ProductResponse.class);
    }
}