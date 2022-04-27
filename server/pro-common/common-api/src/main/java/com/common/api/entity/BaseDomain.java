package com.common.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author guozhenhua
 * @date 2021/05/26
 */
@Getter
@Setter
public class BaseDomain implements Serializable{


    /**
     * 是否删除
     */
    private Short del;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;
    /**
     * 更新时间
     */
    private LocalDateTime update_time;

//    /**
//     * 权限码
//     */
//    private String auth_data_code;
}