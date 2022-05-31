package com.example.orderserver.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.orderserver.model.CreateOrderItem;
import com.example.orderserver.model.CreateOrderParam;
import com.example.orderserver.service.OrderService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
@RefreshScope
public class OrderController {

    @Autowired
    OrderService orderService;

    @Value("${wx.appid:null}")
    private String wxAppId;

    @GetMapping("/getWxAppId")
    public String getWxAppId(){
        return wxAppId;
    }


    @PostMapping("/createOder")
    public String createOder(@RequestBody @Valid CreateOrderParam param){
        return orderService.createOder(param.getList());
    }



}
