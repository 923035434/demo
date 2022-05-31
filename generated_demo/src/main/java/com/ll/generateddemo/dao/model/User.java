package com.ll.generateddemo.dao.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户性别0：未知，1：男，2：女
     */
    private Integer gender;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户展示留言
     */
    private String message;

    /**
     * 用户微信号
     */
    private String wxCode;

    /**
     * 生日
     */
    private LocalDate birthday;

    private static final long serialVersionUID = 1L;
}