package com.example.eurekacustomer.dao.mode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("user_account")
public class UserAccountDO {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Integer userId;

    private BigDecimal money;
}
