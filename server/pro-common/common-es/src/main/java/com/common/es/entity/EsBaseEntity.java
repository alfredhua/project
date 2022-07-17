package com.common.es.entity;

import java.io.Serializable;

/**
 * @author hua
 */
public abstract class EsBaseEntity implements Serializable {

    /**
     * 主键唯一
     * @return id
     */
    public abstract String unique();
}
