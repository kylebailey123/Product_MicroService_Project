package com.capstone.catalog.Catalog.Application.service;

import com.capstone.catalog.Catalog.Application.model.Product;
import com.capstone.catalog.Catalog.Application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProductById(String id) {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            log.info("Thread was interrupted", e);
//        }
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductAllBySku(String id) {
        return productRepository.findAllBySku(id);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductAllByIds(List<String> id) {
        return productRepository.findAllById(id);
    }
}
