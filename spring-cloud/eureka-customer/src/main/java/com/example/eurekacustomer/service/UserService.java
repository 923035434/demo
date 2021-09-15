package com.example.eurekacustomer.service;

import com.example.eureka.provider.controller.model.UserVO;
import com.example.eurekacustomer.UserClient;
import com.example.eurekacustomer.dao.UserAccountDao;
import com.example.eurekacustomer.dao.mode.UserAccountDO;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.var;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    UserClient userClient;


    @Autowired
    UserAccountDao userAccountDao;


    //@GlobalTransactional 和@ShardingTransactionType(TransactionType.BASE)不能混用，如果是跨服务用@GlobalTransactional，
    // 如果不跨服务只是跨库可以用@ShardingTransactionType(TransactionType.BASE)
    //@Transactional
    //@ShardingTransactionType(TransactionType.BASE)
    @GlobalTransactional
    public void addUserInfo(UserVO userInfo) {

        var id = userClient.addUser(userInfo);
        var userAccount = new UserAccountDO(){{
           setMoney(BigDecimal.ZERO);
           setUserId(id);
        }};
        var result = userAccountDao.insert(userAccount);
        if(userInfo.getIsError()!=null&&userInfo.getIsError().equals(1)){
            var i = 1/0;
        }
    }


}
