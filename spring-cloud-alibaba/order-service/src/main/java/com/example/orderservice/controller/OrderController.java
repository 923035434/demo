package com.example.orderservice.controller;

import com.example.orderservice.model.CreateOrderParam;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
