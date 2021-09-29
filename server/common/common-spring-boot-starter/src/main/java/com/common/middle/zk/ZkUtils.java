package com.common.middle.zk;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@Slf4j
@NoArgsConstructor
public class ZkUtils {

    private static CuratorFramework curatorFramework;

    public static void initCuratorFramework(CuratorFramework curatorFrameworkParam) {
        curatorFramework = curatorFrameworkParam;
    }

    public static boolean exist(String path){
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

    public static void create(String path,String value){
        try {
            curatorFramework.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT).forPath(path, value.getBytes());
        }catch (Exception e){
            log.error("create zk node error", e);
            throw new RuntimeException(e);
        }
    }

    public static boolean updateNode(String path,String value){
        try {
            curatorFramework.setData().forPath(path, value.getBytes());
        }catch (Exception e){
            throw new RuntimeException("update node error",e);
        }
        return true;
    }

    public static void deleteNode(String path) {
        try {
            Stat stat=new Stat();
            curatorFramework.delete().withVersion(stat.getVersion()).forPath(path);
        }catch (Exception e){
            throw new RuntimeException("delete node error",e);
        }
    }
}
