package com.test.entity;

import com.common.mybatis.annotation.Column;
import com.common.mybatis.annotation.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(value = "common_test")
public class CommonTest implements Serializable {

    Long id;

    String name;

    @Column(value = "created")
    LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
