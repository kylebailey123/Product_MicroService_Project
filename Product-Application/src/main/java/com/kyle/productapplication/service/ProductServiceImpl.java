package com.kyle.productapplication.service;

import com.kyle.productapplication.client.CatalogClient;
import com.kyle.productapplication.client.InventoryClient;
import com.kyle.productapplication.dto.inventory.ProductInventoryDto;
import com.kyle.productapplication.modal.ProductDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final CatalogClient catalogClient;
    private final InventoryClient inventoryClient;

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "getAvailableProductsByIdFallback")
    @Retry(name = "product-service")
    public ResponseEntity<ProductDto> getAvailableProductById(String uniqueId) {

        ProductDto productsById = catalogClient.getProductsById(uniqueId).getBody();
        if(productsById == null){
            throw new NotFoundException("Product not found Catalog");
        }
        productsById.setAvailable(inventoryClient.getInventoryByProductId(uniqueId));
        return  ResponseEntity.ok().body(productsById);

    }

    private ResponseEntity<ProductDto> getAvailableProductsByIdFallback(String uniqueId, Exception throwable) {
        log.fatal("Fallback method called for getAvailableProductsById with exception: {}", throwable.getMessage(), throwable.fillInStackTrace());
        return ResponseEntity.status(HttpStatusCode.valueOf(503))
        .body(ProductDto
                      .builder()
                      .uniq_id(uniqueId)
                      .available(false)
                      .description("Unavailable")
                      .name_title("Unavailable")
                      .sale_price("0")
                      .sku("Unavailable")
                      .build()
        );
    }


    @Override
    @CircuitBreaker(name = "getAllAvailableProductBySku", fallbackMethod = "getAllAvailableProductBySkuFallback")
    public ResponseEntity<List<ProductDto>> getAllAvailableProductBySku(String sku) {
        List<String> availableProduct = inventoryClient.getAllInventoryByProductSku(sku)
                .stream()
                .filter(inventory -> inventory.getQuantity() > 0)
                .map(ProductInventoryDto::getId )
                .toList();

        return ResponseEntity.ok().body(catalogClient.getAllProductsById(availableProduct));
    }

    private ResponseEntity<List<ProductDto>> getAllAvailableProductBySku(String sku, Exception exception){
        log.fatal("Fallback method called for getAllAvailableProductBySku with exception: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(503))
                .body(List.of());
    }
}
