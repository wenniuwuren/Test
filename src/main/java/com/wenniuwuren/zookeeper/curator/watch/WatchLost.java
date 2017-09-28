package com.wenniuwuren.zookeeper.curator.watch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;


import com.wenniuwuren.Constants;

/**
 * Zookeeper之Watcher监听事件丢失
 *
 * zookeeper原生API注册Watcher需要反复注册，即Watcher触发之后就需要重新进行注册。另外，客户端断开之后重新连接到服务器也是需要
 * 一段时间。这就导致了zookeeper客户端不能够接收全部的zookeeper事件。
 * 因此，对于此问题需要特别注意，不要对 zookeeper 事件进行强依赖。
 *
 * 虽然curator帮开发人员封装了重复注册监听的过程，但是内部依旧需要重复进行注册，而在第一个watcher触发第二个watcher还未注册成功
 * 的间隙，进行节点数据的修改，显然无法收到watcher事件。
 *
 *
 * @author wenniuwuren
 */
public class WatchLost {

    static String path = "/test/watchlost1";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString(Constants.ZK_HOST)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) {
        try {
            client.start();

            // 节点监听：可以监听节点内容变化，还可以监听指定节点是否存在
            final NodeCache nodeCache = new NodeCache(client, path);
            nodeCache.start();

            if (client.checkExists().forPath(path) == null)
                client.create().forPath(path, "0".getBytes());

            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    if (nodeCache.getCurrentData() == null) {
                        System.out.println("节点被删除");
                    } else {
                        System.out.println("节点当前内容为：" + new String(nodeCache.getCurrentData().getData()));
                    }

                }
            });

            client.setData().forPath(path, "1".getBytes());
            client.setData().forPath(path, "2".getBytes());
            client.setData().forPath(path, "3".getBytes());
            client.setData().forPath(path, "4".getBytes());
            client.setData().forPath(path, "5".getBytes());
            client.setData().forPath(path, "6".getBytes());
            client.setData().forPath(path, "7".getBytes());
            client.setData().forPath(path, "8".getBytes());
            client.setData().forPath(path, "9".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}