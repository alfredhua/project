package com.common.zk.config;


import com.common.util.EnvUtil;
import com.common.zk.client.ZkClient;
import lombok.Getter;
import lombok.Setter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

@Getter
@Setter
@Configuration
@ConditionalOnProperty(prefix = "zk",name = "config.enable",havingValue = "true")
public class ZkConfig {

    @Value("zk.config.url")
    private String zkUrl;

    @PostConstruct
    @DependsOn(value = {"commonCore"})
    public void init(){
        String zkUrl = EnvUtil.getEnvironment().getProperty(getZkUrl());
        CuratorFramework curatorFramework = getCuratorFramework(zkUrl);
        ZkClient.initCuratorFramework(curatorFramework);
    }

    public CuratorFramework getCuratorFramework(String zkUrl){
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        CuratorFramework curator = builder.connectString(zkUrl)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(100, 3)).build();
        curator.start();
        return curator;
    }

}
