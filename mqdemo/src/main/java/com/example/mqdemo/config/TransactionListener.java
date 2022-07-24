package com.example.mqdemo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.mqdemo.service.MsgTransactionProcess;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.Message;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RocketMQTransactionListener
public class TransactionListener implements RocketMQLocalTransactionListener, ApplicationContextAware {

    ApplicationContext applicationContext;

    private Map<String,MsgTransactionProcess> transactionProcessMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    private MsgTransactionProcess getMsgTransactionProcess(Message msg) {
        String topic = (String)msg.getHeaders().get(RocketMQUtil.toRocketHeaderKey(RocketMQHeaders.TOPIC));
        //双向检察锁创建处理对象的map
        if(CollectionUtils.isEmpty(transactionProcessMap)){
            synchronized (transactionProcessMap){
                if(CollectionUtils.isEmpty(transactionProcessMap)){
                    Map<String, MsgTransactionProcess> beansOfType = applicationContext.getBeansOfType(MsgTransactionProcess.class);
                    if(!CollectionUtils.isEmpty(beansOfType)){
                        for (String beanName : beansOfType.keySet()){
                            MsgTransactionProcess msgTransactionProcess = beansOfType.get(beanName);
                            String topicStr = msgTransactionProcess.topic();
                            if(StringUtils.isNotEmpty(topicStr)){
                                transactionProcessMap.put(topicStr,msgTransactionProcess);
                            }else {
                                throw new RuntimeException(String.format("%s的topic()方法不能返回为空",beanName));
                            }
                        }
                    }
                }
            }
        }
        MsgTransactionProcess msgTransactionProcess = transactionProcessMap.get(topic);
        return msgTransactionProcess;
    }


    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        MsgTransactionProcess msgTransactionProcess = this.getMsgTransactionProcess(msg);
        try{
            if(msgTransactionProcess!=null){
                msgTransactionProcess.executeTransaction(arg);
                System.out.printf("    # COMMIT # Simulating msg %s related local transaction exec succeeded! ### %n", msg.getPayload());
                return RocketMQLocalTransactionState.COMMIT;
            }
        }catch (Exception e){
            System.out.printf("    # ROLLBACK # Simulating %s related local transaction exec failed! %n", msg.getPayload());
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        //不会执行，unknown是模拟发送commit网络请求失败的情况.之后事务会回查
        System.out.printf("    # UNKNOW # Simulating %s related local transaction exec UNKNOWN! \n");
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MsgTransactionProcess msgTransactionProcess = this.getMsgTransactionProcess(message);
        return msgTransactionProcess.checkTransaction(message);
    }


}
