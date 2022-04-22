package com.common.zk.config;


import com.common.util.EnvUtil;
import com.common.zk.client.ZkClient;
import lombok.Getter;
import lombok.Setter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ZkConfig {

    private static final String ZK_URL="zk.config.url";

    public void init(){
        String zkUrl = EnvUtil.getEnvironment().getProperty(ZK_URL);
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
