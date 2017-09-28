package com.wenniuwuren.zookeeper.curator.watch;

import java.nio.charset.Charset;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;


import com.wenniuwuren.Constants;

/**
 * 监听background通知，对于节点的创建或修改则不会触发监听事件
 * @author wenniuwuren
 */
public class BackgroundWatch {

    static String path = "/test/backGroundWatch";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString(Constants.ZK_HOST)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) {
        try {
            client.start();

            if (client.checkExists().forPath(path) == null)
                client.create().forPath(path, "create".getBytes());

            CuratorListener curatorListener = new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println("[op:Watch.eventReceived] " + curatorEvent
                            + ", data=" + new String(curatorFramework.getData().forPath(path), Charset.forName("UTF-8")));
                }
            };

            client.getCuratorListenable().addListener(curatorListener);

            // 在background获取节点数据，即异步获取数据
            client.getData().inBackground().forPath(path);

            // 更新节点（这里不会收到监听回调）
            client.setData().forPath(path, "update1".getBytes());

            // 等待一会收到 watch
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}