package com.example.orderservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrderParam {

    @NotNull(message = "参数不能为空")
    List<CreateOrderItem> list;

}
