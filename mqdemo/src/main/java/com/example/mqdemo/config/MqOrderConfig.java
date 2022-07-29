package com.example.mqdemo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mq.order")
@Data
public class MqOrderConfig {

    private String topic;




}
