package com.message;

import com.common.CommonCore;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@EnableDubbo(scanBasePackages = {"com.message.rpc"})
@MapperScan(basePackages = { "com.message.dao"})
@Import({ CommonCore.class})
@SpringBootApplication
public class MessageCore {

}
