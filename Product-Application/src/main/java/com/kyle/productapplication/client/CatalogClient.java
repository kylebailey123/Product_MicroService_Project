package com.kyle.productapplication.client;

import com.kyle.productapplication.modal.ProductDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "catalog-application" // This is the name of the service we are calling since we are use service discovery url is not needed
        // url = "http://localhost:8082" // This is the url of the service we are calling if we are not using service discovery
)
public interface CatalogClient {

    @GetMapping("/catalog/{id}")
    ResponseEntity<ProductDto> getProductsById(@PathVariable String id);

    @GetMapping("/catalog/")
    List<ProductDto> getAllProductsById(@RequestParam List<String> ids);

    @GetMapping("/catalog/sku/{sku}")
    List<ProductDto> getProductsBySku(@PathVariable String sku);




}
