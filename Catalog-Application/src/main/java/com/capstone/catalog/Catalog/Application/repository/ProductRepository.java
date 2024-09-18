package com.capstone.catalog.Catalog.Application.repository;

import com.capstone.catalog.Catalog.Application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findBySku(String sku);
    List<Product> findAllBySku(String sku);
}
