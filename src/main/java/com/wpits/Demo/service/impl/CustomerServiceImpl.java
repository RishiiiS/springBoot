package com.wpits.Demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wpits.Demo.entity.Customer;
import com.wpits.Demo.entity.Inventory;
import com.wpits.Demo.repositories.CustomerRepository;
import com.wpits.Demo.repositories.InventoryRepository;
import com.wpits.Demo.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public Customer buyProduct(Customer customerRequest) {
        Long productId = customerRequest.getProductId();
        
        Optional<Inventory> optionalInventory = inventoryRepository.findById(productId);
        
        if (!optionalInventory.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        
        Inventory inventory = optionalInventory.get();
        
        if (inventory.getQuantity() < customerRequest.getQuantityPurchased()) {
            throw new RuntimeException("Insufficient stock available");
        }
        
        // Fetch details from inventory automatically
        customerRequest.setProductPurchased(inventory.getProductName());
        customerRequest.setProductTypePurchased(inventory.getProductType());
        customerRequest.setPrice(inventory.getPrice());
        
        // Calculate total price
        Double totalPrice = inventory.getPrice() * customerRequest.getQuantityPurchased();
        customerRequest.setTotalPrice(totalPrice);
        
        // Reduce inventory quantity
        inventory.setQuantity(inventory.getQuantity() - customerRequest.getQuantityPurchased());
        inventoryRepository.save(inventory);
        
        // Save customer purchase record
        return customerRepository.save(customerRequest);
    }

    @Override
    public List<Customer> getAllCustomerPurchases() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerPurchaseById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public void deleteCustomerPurchase(Long id) {
        customerRepository.deleteById(id);
    }
}
