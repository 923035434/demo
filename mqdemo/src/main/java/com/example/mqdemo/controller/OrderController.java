package com.example.mqdemo.controller;


import com.example.mqdemo.product.OrderProduct;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderProduct orderProduct;

    @RequestMapping("/createOrder")
    public void createOrder(Long orderId, BigDecimal price) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        orderProduct.createOrder(orderId,price);
    }

}
