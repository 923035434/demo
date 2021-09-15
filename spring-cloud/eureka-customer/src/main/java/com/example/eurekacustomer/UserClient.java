package com.example.eurekacustomer;

import com.example.eureka.provider.controller.model.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "eureka-provider")
public interface UserClient {

    @PostMapping("/user/addUser")
    Integer addUser(@RequestBody UserVO param);

}
