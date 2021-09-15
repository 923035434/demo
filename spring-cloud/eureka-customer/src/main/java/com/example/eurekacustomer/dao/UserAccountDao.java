package com.example.eurekacustomer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.eurekacustomer.dao.mode.UserAccountDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDao extends BaseMapper<UserAccountDO> {
}
