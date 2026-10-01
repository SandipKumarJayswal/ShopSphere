package com.shopsphere.inventory.controller;

import com.shopsphere.inventory.entity.Inventory;
import com.shopsphere.inventory.service.InventoryService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(
            InventoryService service) {

        this.service = service;
    }

    @PostMapping("/{productId}/add/{quantity}")
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory addStock(
            @PathVariable Long productId,
            @PathVariable Integer quantity) {

        return service.addStock(productId, quantity);
    }

    @GetMapping("/{productId}")
    public Inventory getStock(
            @PathVariable Long productId) {

        return service.getStock(productId);
    }

    @PostMapping("/{productId}/reserve/{quantity}")
    public Inventory reserve(
            @PathVariable Long productId,
            @PathVariable Integer quantity) {

        return service.reserve(productId, quantity);
    }

    @PostMapping("/{productId}/release/{quantity}")
    public Inventory release(
            @PathVariable Long productId,
            @PathVariable Integer quantity) {

        return service.release(productId, quantity);
    }
}