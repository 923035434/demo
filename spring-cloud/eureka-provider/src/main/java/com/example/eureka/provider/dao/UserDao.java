package com.example.eureka.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.eureka.provider.dao.mode.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<UserDO> {
}
