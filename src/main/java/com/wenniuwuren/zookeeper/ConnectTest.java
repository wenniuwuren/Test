package com.wenniuwuren.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * zk 连接测试
 * Created by Yibin_Zhu on 2017/3/26.
 */
public class ConnectTest implements Watcher{
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static final String ip = "192.168.116.128:2181";

    public static void main(String[] args) {
        try {
            ZooKeeper zookeeper = new ZooKeeper(ip, 5000, new ConnectTest());
            System.out.println("zookeeper.getState():" + zookeeper.getState());

            try {
                // 因为 ZooKeeper 是异步创建连接的，所以在这里等待着，服务端回调 process() 后，
                // 才知道是否连接成功
                countDownLatch.await();

                long sessionId = zookeeper.getSessionId();
                byte[] passwd = zookeeper.getSessionPasswd();

                // 错误的 sessionId。会收到服务端的 state:Expired
                zookeeper = new ZooKeeper(ip, 5000, new ConnectTest(), 1l, passwd);

                // 连接上 zookeeper 服务端后，使用 sessionId,passwd 连接可以复用会话
                zookeeper = new ZooKeeper(ip, 5000, new ConnectTest(), sessionId, passwd);
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
