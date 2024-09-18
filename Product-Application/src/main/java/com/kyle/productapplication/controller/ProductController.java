package com.kyle.productapplication.controller;

import com.kyle.productapplication.modal.ProductDto;
import com.kyle.productapplication.service.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getAvailableProductsById(@PathVariable String id) {
        return productService.getAvailableProductById(id);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<List<ProductDto>> getAllAvailableProductsBySk(@PathVariable String sku) {
        return productService.getAllAvailableProductBySku(sku);
    }
}
