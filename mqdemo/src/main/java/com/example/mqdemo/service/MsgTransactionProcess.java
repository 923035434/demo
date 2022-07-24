package com.example.mqdemo.service;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * 消息一致性事务保证接口
 */
public interface MsgTransactionProcess {


    /**
     * 区分那个topic的事务
     * @return
     */
    String topic();


    /**
     * 需要执行事务的操作，如插入数据库
     * @param arg
     */
    void executeTransaction(Object arg);


    /**
     * 用于mq回查事务
     * @param message
     * @return
     */
    RocketMQLocalTransactionState checkTransaction(Message message);



}
