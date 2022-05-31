package com.ll.generateddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GeneratedDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneratedDemoApplication.class, args);
    }

}
