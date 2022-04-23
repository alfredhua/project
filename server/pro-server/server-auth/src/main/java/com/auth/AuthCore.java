package com.auth;

import com.common.CommonCore;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import({ CommonCore.class})
@SpringBootApplication
@MapperScan(basePackages = {"com.auth.dao"})
public class AuthCore {
}
