package com.common.es.entity;

import java.io.Serializable;

public abstract class EEntity implements Serializable {

    /**
     * 主键唯一
     */
    public abstract String unique();
}
