package com.develop.listener;

import com.common.util.CaffeineCacheUtils;
import com.common.zk.ZkUtils;
import com.develop.constants.NodePathEnum;
import com.develop.dto.entity.Deploy;
import com.develop.dao.DeployMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@Component
@Slf4j
public class DevelopNodeListener{

    @Autowired
    DeployMapper deployMapper;

    @Autowired
    CuratorFramework curatorFramework;

    @Autowired
    ZkUtils zkUtils;

    @Bean
    @DependsOn("flywayInitializer")
    public void initDevelopNode(){
        NodePathEnum[] values = NodePathEnum.values();
        List<NodePathEnum> nodePathEnums = Arrays.asList(values);
        nodePathEnums.forEach(nodePathEnum->{
            Deploy deploy = deployMapper.getByName(nodePathEnum.getName());
            String value=nodePathEnum.getDefault_value();
            if (deploy!=null&&!StringUtils.isEmpty(deploy.getName_value())){
                value=deploy.getName_value();
            }
            createNode( nodePathEnum.getNodePath(), value);
            this.addListenerWithNode(nodePathEnum.getName());
        });
    }

    private void createNode(String path,String value){
        if (!zkUtils.exist(path)) {
            zkUtils.create(path,value);
            return;
        }
        zkUtils.updateNode(path,value);
    }

    public CuratorCacheListener listenerNode(){
        return (type, oldData, data) -> {
            if (CuratorCacheListener.Type.NODE_DELETED.equals(type)){
                return;
            }
            String path = data.getPath();
            CaffeineCacheUtils.put(path.substring(1),new String(data.getData()));
        };
    }

    public  void addListenerWithNode(String path) {
        try {
            CuratorCache curatorCache = CuratorCache.builder(curatorFramework, "/"+path).build();
            curatorCache.listenable().addListener(listenerNode());
            curatorCache.start();
        }catch (Exception e){
            throw new RuntimeException("zk node exception",e);
        }
    }
}
