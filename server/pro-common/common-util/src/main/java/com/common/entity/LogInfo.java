package com.common.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hua
 * @date 2022/7/17
 */
@Data
public class LogInfo {

    /***
     * id
     */
    Long id;

    /**
     * 模块名称
     */
    String name;

    /**
     * ip地址
     */
    String ip;

    /**
     * 方法名称
     */
    String method_name;

    /**
     * 登录用户
     */
    String login_user;

    /**
     * 创建时间
     */
    LocalDateTime create_date;


}
