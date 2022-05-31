package com.example.orderserver.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrderItem {

    @NotNull(message = "goodsId不能为空")
    private Long goodsId;

    @Min(value = 1,message = "商品数量不能小于1")
    private Integer qty;

}
