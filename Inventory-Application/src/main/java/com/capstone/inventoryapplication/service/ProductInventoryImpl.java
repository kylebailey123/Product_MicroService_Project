package com.capstone.inventoryapplication.service;

import com.capstone.inventoryapplication.dto.ProductInventoryRequestDto;
import com.capstone.inventoryapplication.model.ProductInventory;
import com.capstone.inventoryapplication.repository.ProductInventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductInventoryImpl implements ProductInventoryService {
    private final ProductInventoryRepository productInventoryRepository;

    @Override
    public boolean isProductAvailable(String id) {
        Optional<ProductInventory> inventory = productInventoryRepository.findById(id);
//        try{
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            log.error("Thread was interrupted", e);
//        }
        return inventory.filter(productInventory -> productInventory.getQuantity() > 0).isPresent();
    }

    @Override
    public List<ProductInventoryRequestDto> getAllProductQuantityBySku(String sku) {
        return productInventoryRepository.findAllById(sku)
                .stream()
                .map(productInventory -> ProductInventoryRequestDto.builder()
                        .id(productInventory.getId())
                        .quantity(productInventory.getQuantity())
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public void updateProductInventory(String id, int quantity) {
        Optional<ProductInventory> inventory = productInventoryRepository.findById(id);
        if (inventory.isPresent()) {
            ProductInventory productInventory = inventory.get();
            productInventory.setQuantity(quantity);
            productInventoryRepository.save(productInventory);
            log.info("Inventory updated successfully for product with id: {}", id);
        }else {
            productInventoryRepository.save(ProductInventory.builder()
                                                    .id(id)
                                                    .quantity(quantity)
                                                    .build());
            log.info("Inventory created successfully for product with id: {}", id);
        }
    }

    @Override
    public void updateProductInventory(List<ProductInventoryRequestDto> inventoryRequestDtos) {
        List<ProductInventory> inventories = inventoryRequestDtos.stream().map(productInventoryRequestDto -> ProductInventory.builder()
                .id(productInventoryRequestDto.getId())
                .quantity(productInventoryRequestDto.getQuantity())
                .build()).toList();
        productInventoryRepository.saveAll(inventories);
        log.info("Inventory updated successfully for {} products", inventories.size());
    }
}
