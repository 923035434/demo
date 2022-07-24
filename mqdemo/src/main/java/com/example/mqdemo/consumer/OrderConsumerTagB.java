package com.example.mqdemo.consumer;

import com.example.mqdemo.model.Order;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * RebalanceImpl
 * protected final ConcurrentMap<String ,SubscriptionData> subscriptionInner
 * 上面结构表示一个应用一个topic只能有一个消费者
 *
 */
//@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",selectorExpression = "B", topic = "${mq.order.topic}", consumerGroup = "${rocketmq.producer.group}")
//@Component
//public class OrderConsumerTagB implements RocketMQListener<Order> {
//
//    @Override
//    public void onMessage(Order message) {
//        System.out.println(message.toString()+"B");
//    }
//
//
//}
