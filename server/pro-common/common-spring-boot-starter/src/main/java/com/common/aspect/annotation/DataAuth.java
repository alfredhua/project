package com.common.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author guozhenhua
 * @date 2021/05/25
 * 数据权限注解，主要是修改mybatis层下的sql语句，默认加上数据权限的条件
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataAuth {

    String value() default "";

}
