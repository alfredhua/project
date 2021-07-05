package com.common.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@Slf4j
public class ZkUtils {

    CuratorFramework curatorFramework;

    public ZkUtils(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }


    public boolean exist(String path){
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            if (stat==null){
                return false;
            }
        } catch (Exception e) {
            log.error("exist zk node error", e);
            throw new RuntimeException(e);
        }

        return true;
    }

    public void create(String path,String value){
        try {
            curatorFramework.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT).forPath(path, value.getBytes());
        }catch (Exception e){
            log.error("create zk node error", e);
            throw new RuntimeException(e);
        }
    }

    public boolean updateNode(String path,String value){
        try {
            curatorFramework.setData().forPath(path, value.getBytes());
        }catch (Exception e){
            throw new RuntimeException("update node error",e);
        }
        return true;
    }

    public void deleteNode(String path) {
        try {
            Stat stat=new Stat();
            curatorFramework.delete().withVersion(stat.getVersion()).forPath(path);
        }catch (Exception e){
            throw new RuntimeException("delete node error",e);
        }
    }


}
