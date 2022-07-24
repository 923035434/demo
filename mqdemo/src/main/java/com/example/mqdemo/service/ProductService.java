package com.example.mqdemo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.mqdemo.config.MqOrderConfig;
import com.example.mqdemo.model.Order;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


@Service
public class ProductService implements MsgTransactionProcess {
    @Autowired
    MqOrderConfig mqOrderConfig;


    @Override
    public String topic() {
        return mqOrderConfig.getTopic();
    }

    @Override
    //@Transaction
    public void executeTransaction(Object arg) {
        JSONObject argObj = (JSONObject)arg;
        System.out.println("订单插入成功:"+arg);
    }

    @Override
    public RocketMQLocalTransactionState checkTransaction(Message message) {
        //默认返回commit
        return RocketMQLocalTransactionState.COMMIT;
    }
}
