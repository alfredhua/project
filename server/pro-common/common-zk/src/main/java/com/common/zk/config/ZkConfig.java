package com.common.zk.config;


import com.common.util.EnvUtil;
import com.common.util.LogUtil;
import com.common.zk.client.ZkClient;
import com.common.zk.enity.ZkProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

@Getter
@Setter
@Configuration
public class ZkConfig {
    ZkProperties zkProperties;
    public ZkConfig(ZkProperties zkProperties) {
        this.zkProperties=zkProperties;
    }

    @PostConstruct
    @DependsOn(value = {"commonCore"})
    public void init(){
        CuratorFramework curatorFramework = getCuratorFramework(zkProperties.getUrl());
        ZkClient.initCuratorFramework(curatorFramework);
        LogUtil.info("zk init success");
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
