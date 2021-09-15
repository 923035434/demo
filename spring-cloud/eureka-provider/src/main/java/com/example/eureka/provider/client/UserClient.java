//package com.example.eureka.provider.client;
//
//import com.example.eureka.provider.controller.model.UserVO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//
//@FeignClient(value = "eureka-provider", contextId = "com.example.eureka.provider.client.UserClient")
//public interface UserClient {
//
//    @PostMapping("/user/addUser")
//    Integer addUser(@RequestBody UserVO param);
//
//}
