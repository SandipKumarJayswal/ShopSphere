package com.shopsphere.product.dto;

import java.math.BigDecimal;

public class ProductResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private boolean active;

    public ProductResponse(
            Long id,
            String name,
            BigDecimal price,
            String description,
            boolean active) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }
}