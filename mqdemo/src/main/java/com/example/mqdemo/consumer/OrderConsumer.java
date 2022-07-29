package com.example.mqdemo.consumer;


import com.example.mqdemo.model.Order;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}", topic = "${mq.order.topic}", consumerGroup = "${rocketmq.producer.group}")
public class OrderConsumer implements RocketMQListener<Order> {

    static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void onMessage(Order message) {
        System.out.println(message.toString()+count.incrementAndGet());
    }
}
