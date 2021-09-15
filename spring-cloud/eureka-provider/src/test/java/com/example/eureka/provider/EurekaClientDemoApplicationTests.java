package com.example.eureka.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.eureka.provider.dao.UserDao;
import com.example.eureka.provider.dao.mode.UserDO;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.example.eurekaclientdemo.dao")
class EurekaClientDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserDao userDao;


    @Test
    public void daoTest(){
        var wrapper = Wrappers.<UserDO>lambdaQuery();
        var result = userDao.selectList(null);
        System.out.println(result);
    }
}
