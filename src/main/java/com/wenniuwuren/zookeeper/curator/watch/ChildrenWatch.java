package com.wenniuwuren.zookeeper.curator.watch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;


import com.wenniuwuren.Constants;

/**
 * @author wenniuwuren
 */
public class ChildrenWatch {

    static String parentPath = "/test/childrenWatch";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString(Constants.ZK_HOST)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) {

        try {
            client.start();

            if (client.checkExists().forPath(parentPath) == null) {
                client.create().forPath(parentPath, "0".getBytes());
            }

            // cacheData表示是否把节点内容缓存起来，如果为true，那么接收到节点列表变更的同时会将获得节点内容。
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, parentPath, true);
            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    System.out.println("事件类型：" + event.getType() + "， 操作节点：" + event.getData().getPath());

                }
            });

            String path1 = "/test/childrenWatch/1";
            if (client.checkExists().forPath(path1) == null) {
                client.create().withMode(CreateMode.PERSISTENT).forPath(path1);
            }

            // PathChildrenCache 不会对二级子节点进行监听，只会对子节点进行监听
            String path11 = "/test/childrenWatch/1/1";
            if (client.checkExists().forPath(path11) == null) {
                client.create().withMode(CreateMode.PERSISTENT).forPath(path11);
            }

            if (client.checkExists().forPath(path1) != null)
                client.delete().deletingChildrenIfNeeded().forPath(path1);

//            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}