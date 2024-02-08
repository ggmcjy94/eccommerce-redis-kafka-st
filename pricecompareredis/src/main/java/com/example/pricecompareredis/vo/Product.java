package com.example.pricecompareredis.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String productGrpId;
    private String productId; // d1fc1031-da1c-40da-9cd1-e9fef3f2a336
    private int price; // 25000 (won)

}
