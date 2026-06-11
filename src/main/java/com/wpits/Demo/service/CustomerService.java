package com.wpits.Demo.service;

import java.util.List;

import com.wpits.Demo.entity.Customer;

public interface CustomerService {

    Customer buyProduct(Customer customerRequest);

    List<Customer> getAllCustomerPurchases();

    Customer getCustomerPurchaseById(Long id);

    void deleteCustomerPurchase(Long id);
}
