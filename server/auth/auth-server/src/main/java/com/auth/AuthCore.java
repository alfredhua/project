package com.auth;

import com.common.CommonCore;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@MapperScan(basePackages = { "com.auth.dao"})
@EnableDubbo(scanBasePackages = {"com.auth.rpc"})
@Import({ CommonCore.class})
@SpringBootApplication
public class AuthCore {
}
