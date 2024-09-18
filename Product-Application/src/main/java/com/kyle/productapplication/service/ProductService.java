package com.kyle.productapplication.service;

import com.kyle.productapplication.modal.ProductDto;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<ProductDto> getAvailableProductById(String uniqueId);
    ResponseEntity<List<ProductDto>> getAllAvailableProductBySku(String sku);
}
