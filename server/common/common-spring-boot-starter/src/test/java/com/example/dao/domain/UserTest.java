package com.example.dao.domain;

import com.common.dao.annotation.Column;
import com.common.dao.annotation.Table;
import com.common.dao.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(value = "user")
public class UserTest extends BaseEntity {

    @Column(value = "name")
    private String name;

    @Column(value = "age")
    private int age;

}
