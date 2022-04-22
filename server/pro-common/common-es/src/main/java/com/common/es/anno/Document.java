package com.common.es.anno;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Document {

    String indexName();

    String type() default "";


    String shards() default "4";

    String replicas() default "1";

    boolean createIndex() default true;

}
