package com.example.eureka.provider.service.impl;

import com.example.eureka.provider.dao.UserDao;
import com.example.eureka.provider.dao.mode.UserDO;
import com.example.eureka.provider.service.UserService;
import lombok.var;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public UserDO getUserById(String id) {
        var result =userDao.selectById(id);
        return result;
    }

    @Override
    public Integer addUser(String name, Integer age, Integer gender) {
        var user = new UserDO(){{
            setName(name);
            setAge(age);
            setGender(gender);
        }};
        var result = userDao.insert(user);
        return user.getId();
    }
}
