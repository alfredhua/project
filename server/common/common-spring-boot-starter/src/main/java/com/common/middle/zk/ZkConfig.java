package com.common.middle.zk;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */

@Configuration
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.zk")
public class ZkConfig {

    private String url;

    @Bean
    public CuratorFramework curatorFramework(){
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        CuratorFramework curatorFramework = builder.connectString(url)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(100, 3)).build();
        curatorFramework.start();
//        if (abstractZkWatchList.isEmpty()){
//            return curatorFramework;
//        }
//        for (AbstractZkNodeListener abstractZkNodeListener:abstractZkWatchList) {
//
//            curatorFramework.create().creatingParentContainersIfNeeded()
//                    .withMode(CreateMode.PERSISTENT)
//                    .forPath(abstractZkNodeListener.getNodePath(), value.getBytes());
//
//            abstractZkNodeListener.setCuratorFramework(curatorFramework)
//                    .addListenerWithNode(abstractZkNodeListener.getNodePath());
//        }
        log.info("zk init");
        return curatorFramework;
    }

}
