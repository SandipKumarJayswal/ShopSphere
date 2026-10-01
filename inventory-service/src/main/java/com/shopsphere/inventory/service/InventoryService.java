package com.shopsphere.inventory.service;

import com.shopsphere.inventory.entity.Inventory;
import com.shopsphere.inventory.repository.InventoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(
            InventoryRepository repository) {

        this.repository = repository;
    }

    public Inventory addStock(
            Long productId,
            Integer quantity) {

        Inventory inventory =
                repository.findByProductId(productId)
                        .orElseGet(Inventory::new);

        inventory.setProductId(productId);

        int current =
                inventory.getQuantity() == null
                        ? 0
                        : inventory.getQuantity();

        inventory.setQuantity(current + quantity);

        return repository.save(inventory);
    }

    public Inventory getStock(Long productId) {

        return repository.findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Inventory not found"));
    }

    @Transactional
    public Inventory reserve(
            Long productId,
            Integer quantity) {

        Inventory inventory = getStock(productId);

        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException(
                    "Insufficient stock");
        }

        inventory.setQuantity(
                inventory.getQuantity() - quantity);

        return repository.save(inventory);
    }

    @Transactional
    public Inventory release(
            Long productId,
            Integer quantity) {

        Inventory inventory = getStock(productId);

        inventory.setQuantity(
                inventory.getQuantity() + quantity);

        return repository.save(inventory);
    }
}