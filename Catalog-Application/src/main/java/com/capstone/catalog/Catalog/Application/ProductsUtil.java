package com.capstone.catalog.Catalog.Application;

import com.capstone.catalog.Catalog.Application.model.Product;
import com.capstone.catalog.Catalog.Application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductsUtil implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        loadCVSFileDataToDatabase("jcpenney_com-ecommerce_sample.csv");
    }

    private void loadCVSFileDataToDatabase(String fileName){
        try( CSVParser csvParser = new CSVParser(
                Files.newBufferedReader(Path.of(new ClassPathResource(fileName).getFile().getPath())),
        CSVFormat.DEFAULT
                .builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .build())) {
            log.info("Loading data from CSV file: {}", fileName);
            csvParser.getRecords().forEach(record -> productRepository.save(
                    Product.builder()
                    .uniq_id(record.get("uniq_id"))
                    .sku(record.get("sku"))
                    .name_title(record.get("name_title"))
                    .description(record.get("description"))
                    .list_price(record.get("list_price"))
                    .sale_price(record.get("sale_price"))
                    .category(record.get("category"))
                    .category_tree(record.get("category_tree"))
                    .average_product_rating(record.get("average_product_rating"))
                    .product_url(record.get("product_url"))
                    .product_image_urls(record.get("product_image_urls"))
                    .brand(record.get("brand"))
                    .total_number_reviews(record.get("total_number_reviews"))
                    .reviews(record.get("reviews"))
                    .build()));
            log.info("Data loaded successfully");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}
