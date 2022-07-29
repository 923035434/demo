package com.example.mqdemo.config;


import org.apache.commons.lang3.math.NumberUtils;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RocketMQTemplateBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof RocketMQTemplate) {
            RocketMQTemplate rocketMQTemplate = (RocketMQTemplate) bean;
            rocketMQTemplate.setMessageQueueSelector(new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    int hashCode = arg.hashCode();
                    if(arg instanceof String){
                        String str = (String)arg;
                        if(NumberUtils.isParsable(str)){
                            hashCode = Long.valueOf(str).hashCode();
                        }
                    }
                    int value = hashCode % mqs.size();
                    if (value < 0) {
                        value = Math.abs(value);
                    }
                    return mqs.get(value);
                }
            });
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }



}
