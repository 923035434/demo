package com.example.eureka.provider.dao.mode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.micrometer.core.instrument.Meter;
import lombok.Data;

@Data
@TableName("user")
public class UserDO {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;
}
