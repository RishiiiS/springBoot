package com.wpits.Demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpits.Demo.entity.Inventory;
import com.wpits.Demo.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/inventory/add")
    public ResponseEntity<?> addProduct(@RequestBody Inventory inventory) {
        if (inventory.getProductName() == null || inventory.getProductName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name cannot be empty");
        }
        if (inventory.getPrice() == null || inventory.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Price cannot be less than 0");
        }
        if (inventory.getQuantity() == null || inventory.getQuantity() < 0) {
            return ResponseEntity.badRequest().body("Quantity cannot be less than 0");
        }

        Inventory savedInventory = inventoryService.addProduct(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventory);
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getAllProducts() {
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Inventory inventory = inventoryService.getProductById(id);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @GetMapping("/inventory/product/{productName}")
    public ResponseEntity<?> getProductByName(@PathVariable String productName) {
        Inventory inventory = inventoryService.getProductByName(productName);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with name " + productName + " not found");
    }

    @PutMapping("/inventory/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Inventory inventoryDetails) {
        if (inventoryDetails.getProductName() == null || inventoryDetails.getProductName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name cannot be empty");
        }
        if (inventoryDetails.getPrice() == null || inventoryDetails.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Price cannot be less than 0");
        }
        if (inventoryDetails.getQuantity() == null || inventoryDetails.getQuantity() < 0) {
            return ResponseEntity.badRequest().body("Quantity cannot be less than 0");
        }

        Inventory updatedInventory = inventoryService.updateProduct(id, inventoryDetails);
        if (updatedInventory != null) {
            return ResponseEntity.ok(updatedInventory);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found to update");
    }

    @DeleteMapping("/inventory/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Inventory inventory = inventoryService.getProductById(id);
        if (inventory != null) {
            inventoryService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found to delete");
    }
}
