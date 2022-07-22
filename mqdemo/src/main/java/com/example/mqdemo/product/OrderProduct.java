package com.example.mqdemo.product;


import com.alibaba.fastjson.JSON;
import com.example.mqdemo.config.MqOrderConfig;
import com.example.mqdemo.model.Order;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderProduct {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @Autowired
    private MqOrderConfig mqOrderConfig;


    public void createOrder(Long orderId, BigDecimal price) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Order order = new Order(orderId, LocalDateTime.now(), price);
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(mqOrderConfig.getTopic(), order, String.valueOf(order.getOrderId()));
//        DefaultMQProducer producer = rocketMQTemplate.getProducer();
//        Message message = RocketMQUtil.convertToRocketMessage(new SimpleMessageConverter(), "utf8",
//                mqOrderConfig.getTopic(), MessageBuilder.withPayload(JSON.toJSONString(order)).build());
//        SendResult send = producer.send(message, rocketMQTemplate.getMessageQueueSelector(), order.getOrderId(), producer.getSendMsgTimeout());
    }




}
