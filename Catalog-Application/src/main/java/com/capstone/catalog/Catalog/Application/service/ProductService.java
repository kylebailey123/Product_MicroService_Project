package com.capstone.catalog.Catalog.Application.service;

import com.capstone.catalog.Catalog.Application.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(String id);
    List<Product> getProductAllByIds(List<String> id);
    List<Product> getProductAllBySku(String id);
    List<Product> getAllProduct();
}
