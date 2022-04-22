package com.common.generator.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2018/12/21
 */
@Getter
@Setter
public class ColumnEntity {

    private String tableColumnName;

    private String humpTableColumnName;

    private String name;

    private String type;

    private String comment;

    private String humpName;

}
