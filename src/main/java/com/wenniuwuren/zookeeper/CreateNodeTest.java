package com.wenniuwuren.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * zk 创建节点测试，同步创建、异步创建
 * Created by Yibin_Zhu on 2017/3/26.
 */
public class CreateNodeTest implements Watcher{
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zookeeper = new ZooKeeper("192.168.99.110:2181", 5000, new CreateNodeTest());
            System.out.println(zookeeper.getState());

            try {
                countDownLatch.await();

                // 同步创建 临时节点
                String path1 = zookeeper.create("/zk-test-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL);
                System.out.println("Success create znode: " + path1);

                // 同步创建 临时有序节点
                String path2 = zookeeper.create("/zk-test-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL_SEQUENTIAL);
                System.out.println("Success create znode: " + path1);

                // 异步创建
                zookeeper.create("/zk-test-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL_SEQUENTIAL, new MyStringCallack(), "i am context");

                // sleep 等待异步创建的回调结果
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException ie) {
                System.out.println("ZooKeeper session established");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }


}
