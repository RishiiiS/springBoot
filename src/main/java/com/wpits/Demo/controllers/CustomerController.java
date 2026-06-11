package com.wpits.Demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpits.Demo.entity.Customer;
import com.wpits.Demo.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer/buy")
    public ResponseEntity<?> buyProduct(@RequestBody Customer customerRequest) {
        if (customerRequest.getProductId() == null) {
            return ResponseEntity.badRequest().body("productId is required");
        }
        if (customerRequest.getQuantityPurchased() == null || customerRequest.getQuantityPurchased() <= 0) {
            return ResponseEntity.badRequest().body("quantityPurchased must be greater than 0");
        }
        
        try {
            Customer savedCustomer = customerService.buyProduct(customerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
        } catch (RuntimeException e) {
            // Returns custom error messages like "Product not found" or "Insufficient stock available"
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomerPurchases() {
        return ResponseEntity.ok(customerService.getAllCustomerPurchases());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerPurchaseById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerPurchaseById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer purchase not found");
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<?> deleteCustomerPurchase(@PathVariable Long id) {
        Customer customer = customerService.getCustomerPurchaseById(id);
        if (customer != null) {
            customerService.deleteCustomerPurchase(id);
            return ResponseEntity.ok("Customer purchase deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer purchase not found to delete");
    }
}
