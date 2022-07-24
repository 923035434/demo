package com.example.mqdemo.consumer;

import com.example.mqdemo.model.Order;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

//@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",consumeMode = ConsumeMode.ORDERLY,selectorExpression = "A||B", topic = "${mq.order.topic}", consumerGroup = "${rocketmq.producer.group}")
//@Component
public class OrderConsumerTagA implements RocketMQListener<Order> {


    @Override
    public void onMessage(Order message) {
        System.out.println(message.toString()+"AB");
    }

}
