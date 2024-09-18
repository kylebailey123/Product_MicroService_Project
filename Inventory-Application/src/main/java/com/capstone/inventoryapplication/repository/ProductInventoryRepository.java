package com.capstone.inventoryapplication.repository;

import com.capstone.inventoryapplication.model.ProductInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, String> {
    List<ProductInventory> findAllById(String uniq_id);
}
