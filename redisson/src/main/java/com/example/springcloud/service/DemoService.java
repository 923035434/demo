package com.example.springcloud.service;


import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    RedissonClient redissonClient;






}
