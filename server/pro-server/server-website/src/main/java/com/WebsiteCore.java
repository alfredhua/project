package com;

import com.common.CommonCore;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@MapperScan(basePackages = { "com.website.dao","com.blog.dao"})
@Import({CommonCore.class})
@SpringBootApplication
public class WebsiteCore {
}
