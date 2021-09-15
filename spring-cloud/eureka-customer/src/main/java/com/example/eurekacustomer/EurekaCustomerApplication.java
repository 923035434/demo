package com.example.eurekacustomer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.example.eurekacustomer.dao")
public class EurekaCustomerApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaCustomerApplication.class, args);
    }

}
