package com.wenniuwuren.zookeeper;

import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 检查节点是否存在
 * Created by Yibin_Zhu on 2017/4/22.
 */
public class NodeExistCheck implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static final String ip = "192.168.116.128:2181";

    private static ZooKeeper zk;

    public static void main(String[] args) {
        try {
            String path = "/zk-bookkk";
            zk = new ZooKeeper(ip, 5000, new NodeExistCheck());

            countDownLatch.await();

            // 检查节点是否存在，同时注册 Watcher
            zk.exists(path, true);

            zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            // version=-1 不要求使用乐观锁
            zk.setData(path, "123".getBytes(), -1);

            // 子节点操作不会通知
            zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            zk.delete(path + "/c1", -1);

            zk.delete(path, -1);

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    countDownLatch.countDown();
                } else if (Event.EventType.NodeCreated == watchedEvent.getType()) {
                    System.out.println("Node(" + watchedEvent.getPath() + ") Created");
                    // 再注册监听，后续操作才会被监听
                    zk.exists(watchedEvent.getPath(), true);
                } else if (Event.EventType.NodeDeleted == watchedEvent.getType()) {
                    System.out.println("Node(" + watchedEvent.getPath() + ") Deleted");
                    zk.exists(watchedEvent.getPath(), true);
                } else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                    System.out.println("Node(" + watchedEvent.getPath() + ") DataChanged");
                    zk.exists(watchedEvent.getPath(), true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
