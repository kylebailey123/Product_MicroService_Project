package com.capstone.catalog.Catalog.Application.controller;

import com.capstone.catalog.Catalog.Application.model.Product;
import com.capstone.catalog.Catalog.Application.service.ProductServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalog")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("sku/{sku}")
    public ResponseEntity<List<Product>> getAllProductBySku(@PathVariable String sku){
        return ResponseEntity.ok().body(productService.getProductAllBySku(sku));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        Optional<Product> productById = productService.getProductById(id);
        return productById.map(product -> ResponseEntity.ok().body(product)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("")
    public List<Product> getAll(){
        return productService.getAllProduct();
    }

    @GetMapping("/")
    public List<Product> getAll(@RequestParam List<String> ids){
        return productService.getProductAllByIds(ids);
    }
}
