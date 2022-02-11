package com.develop.listener;

import com.common.middle.zk.ZkUtil;
import com.common.util.CaffeineCacheUtil;
import com.common.util.LogUtil;
import com.develop.constants.NodePathEnum;
import com.develop.dao.DeployMapper;
import com.pro.develop.dto.entity.Deploy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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


    @Bean
    @DependsOn(value={"flywayInitializer","initAllMiddle"})
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
        LogUtil.info("develop init zk node success");
    }

    private void createNode(String path,String value){
        if (!ZkUtil.exist(path)) {
            ZkUtil.create(path,value);
            return;
        }
        ZkUtil.updateNode(path,value);
    }

    public CuratorCacheListener listenerNode(){
        return (type, oldData, data) -> {
            if (CuratorCacheListener.Type.NODE_DELETED.equals(type)){
                return;
            }
            String path = data.getPath();
            CaffeineCacheUtil.put(path.substring(1),new String(data.getData()));
        };
    }

    public  void addListenerWithNode(String path) {
        try {

            CuratorCache curatorCache = CuratorCache.builder(ZkUtil.getCuratorFramework(), "/"+path).build();
            curatorCache.listenable().addListener(listenerNode());
            curatorCache.start();
        }catch (Exception e){
            throw new RuntimeException("zk node exception",e);
        }
    }
}
