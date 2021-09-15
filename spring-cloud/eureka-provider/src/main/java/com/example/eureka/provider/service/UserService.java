package com.example.eureka.provider.service;

import com.example.eureka.provider.dao.mode.UserDO;

public interface UserService {

   UserDO getUserById(String id);

   Integer addUser(String name,Integer age,Integer gender);


}
