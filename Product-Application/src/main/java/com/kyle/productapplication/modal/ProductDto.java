package com.kyle.productapplication.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductDto {
    private String uniq_id;
    private String sku;
    private String name_title;
    private String description;
    private String sale_price;
    private boolean available;

}
