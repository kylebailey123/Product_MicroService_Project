package com.kyle.productapplication.client;

import com.kyle.productapplication.dto.inventory.ProductInventoryDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "inventory-application" // This is the name of the service we are calling since we are use service discovery url is not needed
        // url = "http://localhost:8085" // This is the url of the service we are calling if we are not using service discovery
)
public interface InventoryClient {
    @GetMapping("/product-inventory/is-product-available/{id}")
    boolean getInventoryByProductId(@PathVariable String id);

    @GetMapping("/product-inventory//get-all-product-quantity-by-sku/{sku}")
    List<ProductInventoryDto> getAllInventoryByProductSku(@PathVariable String sku);
}
