package com.common;

import com.common.middle.zk.ZkUtil;
import com.common.util.EnvUtil;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@EnableAutoConfiguration
@ComponentScan
public class CommonCore {

    @Autowired
    Environment environment;

    @Autowired
    CuratorFramework curatorFramework;

    @PostConstruct
    public void initEnvironment(){
        EnvUtil.initEnv(environment);
        ZkUtil.initCuratorFramework(curatorFramework);
    }

}
