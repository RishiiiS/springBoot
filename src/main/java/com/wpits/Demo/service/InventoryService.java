package com.wpits.Demo.service;

import java.util.List;

import com.wpits.Demo.entity.Inventory;

public interface InventoryService {

    Inventory addProduct(Inventory inventory);

    Inventory getProductById(Long id);

    Inventory getProductByName(String productName);

    List<Inventory> getAllProducts();

    Inventory updateProduct(Long id, Inventory inventoryDetails);

    void deleteProduct(Long id);
}
