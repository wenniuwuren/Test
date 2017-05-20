package com.wenniuwuren.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 创建节点
 * Created by Yibin_Zhu on 2017/5/13.
 */
public class CreateSession {

    public static final String ip = "192.168.116.128:2181";
    public static final String path = "/zk-book/c1";

    public static void main(String[] args) {
        try {
            // 每次重试间隔为指数增长  1000初始sleep时间，3重试次数
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = null;
//                    client = CuratorFrameworkFactory.newClient(ip, 5000, 3000, retryPolicy);
            // fluent 风格
            client = CuratorFrameworkFactory.builder().connectString(ip).sessionTimeoutMs(5000)
                    .retryPolicy(retryPolicy)
                    .build();

            client.start();
            client.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(path, "init".getBytes());

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
