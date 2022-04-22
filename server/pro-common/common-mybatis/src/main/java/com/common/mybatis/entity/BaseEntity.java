package com.common.mybatis.entity;

import com.common.mybatis.annotation.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity {

    @Column(value = "id")
    Long id;

    @Column(value = "create_time")
    LocalDateTime createTime;

    @Column(value = "update_time")
    LocalDateTime updateTime;

    @Column(value = "modifier")
    String modifier;

    @Column(value = "del")
    short del;

}
