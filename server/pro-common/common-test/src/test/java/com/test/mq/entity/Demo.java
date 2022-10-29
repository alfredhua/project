package com.test.mq.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo implements Serializable {

    String id;

    String name;

    Long time;
}
