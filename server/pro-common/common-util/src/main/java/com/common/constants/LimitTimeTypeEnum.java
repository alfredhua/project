package com.common.constants;

public enum LimitTimeTypeEnum {

    /**
     * 不限制
     */
    NULL,

    /**
     * 令牌桶限制，执行完一个释放一个令牌
     */
    COUNT_LIMIT,

    /**
     * 超时，默认释放
     */
    TIME_LIMIT

    ;


}
