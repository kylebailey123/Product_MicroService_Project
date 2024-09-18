package com.capstone.inventoryapplication.service;


import com.capstone.inventoryapplication.dto.ProductInventoryRequestDto;

import java.util.List;

public interface ProductInventoryService {
    boolean isProductAvailable(String id);
    List<ProductInventoryRequestDto> getAllProductQuantityBySku(String sku);
    void updateProductInventory(String id, int quantity);
    void updateProductInventory(List<ProductInventoryRequestDto> inventoryRequestDtos);
}
