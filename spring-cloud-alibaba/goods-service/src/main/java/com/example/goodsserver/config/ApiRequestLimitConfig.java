package com.example.goodsserver.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("api.request")
public class ApiRequestLimitConfig {


    /**
     * key：nacos配置的limit key，value：每秒限流大小
     */
    private Map<String,Integer> limit;




}
