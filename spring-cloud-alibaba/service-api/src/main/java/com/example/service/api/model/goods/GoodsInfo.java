package com.example.service.api.model.goods;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsInfo {

    private Long id;


    private String name;


    private BigDecimal price;

}
