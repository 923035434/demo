package com.example.eurekacustomer.controller;


import com.example.eureka.provider.controller.model.UserVO;
import com.example.eurekacustomer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public void addUser(@RequestBody UserVO param) throws Exception {
        userService.addUserInfo(param);
    }


}
