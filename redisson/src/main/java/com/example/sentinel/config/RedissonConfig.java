//package com.example.springcloud.config;
//
//import org.springcloud.Redisson;
//import org.springcloud.api.RedissonClient;
//import org.springcloud.codec.JsonJacksonCodec;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springcloud.config.Config;
//
//@Configuration
//public class RedissonConfig {
//
//    @Bean
//    RedissonClient redissonClient(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://192.168.66.133:6379");
//        config.setCodec(new JsonJacksonCodec());
//        RedissonClient springcloud = Redisson.create(config);
//        return springcloud;
//    }
//
//
//
//
//}
