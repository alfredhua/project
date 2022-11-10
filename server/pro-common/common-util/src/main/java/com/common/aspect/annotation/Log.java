package com.common.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author hua
 * @date 2022/7/17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Log {

    /**
     * 日志模块名称
     * @return
     */
    String name();

}
