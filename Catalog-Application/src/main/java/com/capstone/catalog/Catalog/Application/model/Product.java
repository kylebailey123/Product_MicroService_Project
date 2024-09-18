package com.capstone.catalog.Catalog.Application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String uniq_id;
    private String sku;
    private String name_title;
    @Column(length = 30000)
    private String description;
    private String list_price;
    private String sale_price;
    private String category;
    private String category_tree;
    private String  average_product_rating;
    @Column(length = 30000)
    private String product_url;
    @Column(length = 30000)
    private String product_image_urls;
    private String brand;
    private String total_number_reviews;
    @Column(length = 30000)
    private String  reviews;
}
