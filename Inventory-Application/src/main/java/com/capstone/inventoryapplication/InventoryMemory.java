package com.capstone.inventoryapplication;

import com.capstone.inventoryapplication.model.ProductInventory;
import com.capstone.inventoryapplication.repository.ProductInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryMemory implements CommandLineRunner {
    private final ProductInventoryRepository inventoryRepository;
    @Override
    public void run(String... args) {
        loadCVSFileDataToDatabase("jcpenney_com-ecommerce_sample.csv");
    }

    private void loadCVSFileDataToDatabase(String fileName){
        try(CSVParser csvParser = new CSVParser(
                Files.newBufferedReader(Path.of(new ClassPathResource(fileName).getFile().getPath())),
                CSVFormat.DEFAULT
                        .builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .setIgnoreHeaderCase(true)
                        .build())) {
            log.info("Loading data from CSV file: {}", fileName);
            csvParser.getRecords().forEach(record -> inventoryRepository.save(
                    ProductInventory.builder()
                            .id(record.get("uniq_id"))
                            .quantity(new Random().nextInt(0,25))
                            .build()));
            log.info("Data loaded successfully");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
