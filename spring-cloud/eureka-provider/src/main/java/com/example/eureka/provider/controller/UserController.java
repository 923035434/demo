package com.example.eureka.provider.controller;


import com.example.eureka.provider.controller.model.UserVO;
import com.example.eureka.provider.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


    @PostMapping("/addUser")
    public Integer addUser(@RequestBody UserVO param){
        return userService.addUser(param.getName(),param.getAge(),param.getGender());
    }



}
