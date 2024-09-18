package com.kyle.productapplication.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductInventoryDto {
    private String id;
    private int quantity;
}
