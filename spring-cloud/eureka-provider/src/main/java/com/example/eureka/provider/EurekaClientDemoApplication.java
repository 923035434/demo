package com.example.eureka.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.example.eureka.provider.dao")
public class EurekaClientDemoApplication {

   // brave.mysql8.TracingQueryInterceptor
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientDemoApplication.class, args);
    }

}
