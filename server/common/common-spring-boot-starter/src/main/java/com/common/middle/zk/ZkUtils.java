package com.common.middle.zk;

import com.common.util.LoadPropertiesUtil;
import com.common.util.LogUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Properties;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
public class ZkUtils {

    private static CuratorFramework curatorFramework;

    private static final String CONFIG_FILE="zk.config.file";

    private static final Properties properties;

    static {
        properties= LoadPropertiesUtil.loadConfig(CONFIG_FILE);
        if (!ObjectUtils.isEmpty(properties)){
            String url = properties.getProperty("zk.url", "localhost");
            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
            CuratorFramework curator = builder.connectString(url)
                    .sessionTimeoutMs(5000)
                    .retryPolicy(new ExponentialBackoffRetry(100, 3)).build();
            curator.start();
            curatorFramework=curator;
        }
    }

    public static String getZkProperty(String property){
        return properties.getProperty(property);
    }

    public static boolean exist(String path){
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            if (stat==null){
                return false;
            }
        } catch (Exception e) {
            LogUtil.error("exist zk node error", e);
            throw new RuntimeException(e);
        }

        return true;
    }

    public static void create(String path,String value){
        try {
            curatorFramework.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT).forPath(path, value.getBytes());
        }catch (Exception e){
            LogUtil.error("create zk node error", e);
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
