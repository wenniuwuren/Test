package com.wenniuwuren.zookeeper.curator.watch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;


import com.wenniuwuren.Constants;

/**
 * 只监听一次
 * @author wenniuwuren
 */
public class WatchOneTime {

    static String path = "/test/watchOneTime";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString(Constants.ZK_HOST)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) {

        try {
            client.start();

            if (client.checkExists().forPath(path) == null)
                client.create().forPath(path, "create".getBytes());

            client.getData().usingWatcher(new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("[op:Watch.process] " + event);
                }
            }).forPath(path);

            System.out.println("listening path=" + path);

            // 第一次更新数据
            client.setData().forPath(path, "update1".getBytes());

            // 第二次更新数据
            client.setData().forPath(path, "update2".getBytes());

            // 等待一会收到 watch
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}