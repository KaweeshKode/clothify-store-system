package com.clothifystore.crm.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private Integer productId;
    private String productName;
    private String size;
    private Double price;
    private Integer quantity;
    private String category;
}
