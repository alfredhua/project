package com.develop;

import com.common.CommonCore;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@MapperScan(basePackages = { "com.develop.dao"})
@EnableDubbo(scanBasePackages = "com.develop.rpc")
@Import({ CommonCore.class})
@SpringBootApplication
public class DevelopCore {
}
