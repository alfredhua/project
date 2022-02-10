package com.common.domain.constants;

/**
 * Created by guozhenhua
 * date 2020/3/1.
 */
public enum  LimitTimeTypeEnum {

    NULL,  //不进行限流
    LIMIT,  //限制每分钟内次数
    TIMEOUT;
}
