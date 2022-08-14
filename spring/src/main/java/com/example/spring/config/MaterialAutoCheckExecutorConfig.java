package com.example.spring.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
//
//@Configuration
//@EnableAsync
//@ConfigurationProperties(prefix = "content.check")
public class MaterialAutoCheckExecutorConfig {

    private int corePoolSize = 2;
    private int maxPoolSize = 5;
    private int queueCapacity = 6000;
    private String namePrefix = "materialAutoCheck-";

    /**
     *素材自动审核线程池
     * @return
     */
    @Bean("materialAutoCheckExecutor")
    public Executor productRatingExecutor(){
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(corePoolSize);
        pool.setMaxPoolSize(maxPoolSize);
        pool.setQueueCapacity(queueCapacity);
        pool.setKeepAliveSeconds(5);
        pool.setThreadNamePrefix(namePrefix);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        pool.initialize();
        return pool;
    }


}
