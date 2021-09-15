package com.example.eurekacustomer;

import com.example.eureka.provider.controller.model.UserVO;
import com.example.eurekacustomer.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.example.eurekacustomer.dao")
class EurekaCustomerApplicationTests {

    UserService userService;


    @Test
    public void addUserInfoTest() throws Exception {
        userService.addUserInfo(new UserVO(){{
            setAge(10);
            setGender(1);
            setName("小刚");
        }});

    }

}
