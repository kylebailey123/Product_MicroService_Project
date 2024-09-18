package com.capstone.inventoryapplication.controller;

import com.capstone.inventoryapplication.dto.ProductInventoryRequestDto;
import com.capstone.inventoryapplication.service.ProductInventoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-inventory")
@RequiredArgsConstructor
public class ProductInventoryController {
    private final ProductInventoryService productInventoryService;

    @GetMapping("/is-product-available/{id}")
    public boolean isProductAvailable(@PathVariable("id") String id) {
        return productInventoryService.isProductAvailable(id);
    }

    @GetMapping("/get-all-product-quantity-by-sku/{sku}")
    public List<ProductInventoryRequestDto> isProductUnavailable(@PathVariable String sku) {
        return productInventoryService.getAllProductQuantityBySku(sku);
    }

    @PostMapping("/update-product-inventory/{id}/{quantity}")
    public void updateProductInventory(@PathVariable("id") String id, @PathVariable("quantity") int quantity) {
        productInventoryService.updateProductInventory(id, quantity);
    }

    @PostMapping("/update-product-inventory")
    public void updateProductInventory(@RequestBody List<ProductInventoryRequestDto> productInventories) {
        productInventoryService.updateProductInventory(productInventories);
    }
}
