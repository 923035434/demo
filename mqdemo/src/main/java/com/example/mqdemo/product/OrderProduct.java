package com.example.mqdemo.product;


import com.alibaba.fastjson.JSON;
import com.example.mqdemo.config.MqOrderConfig;
import com.example.mqdemo.model.Order;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderProduct {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @Autowired
    private MqOrderConfig mqOrderConfig;


    /**
     * 顺序发送
     * @param orderId
     * @param price
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    public void createOrder(Long orderId, BigDecimal price) {
        Order order = new Order(orderId, LocalDateTime.now(), price);
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(mqOrderConfig.getTopic(), order, String.valueOf(order.getOrderId()));
//        DefaultMQProducer producer = rocketMQTemplate.getProducer();
//        Message message = RocketMQUtil.convertToRocketMessage(new SimpleMessageConverter(), "utf8",
//                mqOrderConfig.getTopic(), MessageBuilder.withPayload(JSON.toJSONString(order)).build());
//        SendResult send = producer.send(message, rocketMQTemplate.getMessageQueueSelector(), order.getOrderId(), producer.getSendMsgTimeout());
    }


    /**
     * 按tag发送
     * @param orderId
     * @param price
     * @param tag
     */
    public void createOrderByTag(Long orderId, BigDecimal price,String tag){
        Order order = new Order(orderId, LocalDateTime.now(), price);
        if(StringUtils.isNotEmpty(tag)){
            SendResult sendResult = rocketMQTemplate.syncSend(String.format("%s:%s", mqOrderConfig.getTopic(), tag), order);
        }
    }


    /**
     *事务发送消息
     */
    public void createOrderTransaction(Long orderId){
        Random random = new Random();
        Order order = new Order(orderId, LocalDateTime.now(), BigDecimal.valueOf(random.nextInt(10)+1));
        String transactionId = UUID.randomUUID().toString();
        Message<Order> msg = MessageBuilder.withPayload(order)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                .setHeader(RocketMQHeaders.KEYS,order.getOrderId())
                .build();
        SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(mqOrderConfig.getTopic(), msg, JSON.toJSON(order));
        System.out.printf("------rocketMQTemplate send Transactional msg  transactionId = %s body = %s , sendResult=%s %n",transactionId,msg.getPayload(), sendResult.getSendStatus());
    }





}
