package com.wpits.Demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wpits.Demo.entity.Inventory;
import com.wpits.Demo.repositories.InventoryRepository;
import com.wpits.Demo.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory addProduct(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getProductById(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.orElse(null);
    }

    @Override
    public Inventory getProductByName(String productName) {
        Optional<Inventory> inventory = inventoryRepository.findByProductName(productName);
        return inventory.orElse(null);
    }

    @Override
    public List<Inventory> getAllProducts() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory updateProduct(Long id, Inventory inventoryDetails) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isPresent()) {
            Inventory existingInventory = optionalInventory.get();
            
            existingInventory.setProductName(inventoryDetails.getProductName());
            existingInventory.setProductType(inventoryDetails.getProductType());
            existingInventory.setPrice(inventoryDetails.getPrice());
            existingInventory.setQuantity(inventoryDetails.getQuantity());
            
            // We do not update createdAt as per requirements
            return inventoryRepository.save(existingInventory);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        inventoryRepository.deleteById(id);
    }
}
