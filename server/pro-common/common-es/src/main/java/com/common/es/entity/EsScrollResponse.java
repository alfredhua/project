package com.common.es.entity;

import lombok.Data;

import java.util.List;

@Data
public class EsScrollResponse<T> {

    /**
     * 存放数据
     */
    private List<T> data;

    /**
     * 指定游标
     */
    private String scrollId;
}
