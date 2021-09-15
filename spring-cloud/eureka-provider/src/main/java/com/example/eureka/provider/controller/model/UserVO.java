package com.example.eureka.provider.controller.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserVO {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Min(value = 1,message = "年龄不能小于1")
    private Integer age;

    @Range(min = 0,max = 1,message = "性别输入错误")
    private Integer gender;

    private Integer isError;
}
