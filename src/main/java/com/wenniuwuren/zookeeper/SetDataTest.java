package com.wenniuwuren.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Yibin_Zhu on 2017/4/15.
 */
public class SetDataTest implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    public static final String ip = "192.168.116.128:2181";

    public static void main(String[] args) {
        try {
            String path = "/zk-boooooooook";
            zk = new ZooKeeper(ip, 5000, new SetDataTest());

            countDownLatch.await();

            zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            // version=-1 说明根据最新版本进行更新
            Stat stat = zk.setData(path, "456".getBytes(), -1);
            System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());

            // 带着上一次更新的 version 才能正确更新
            Stat stat2 = zk.setData(path, "456".getBytes(), stat.getVersion());
            System.out.println(stat2.getCzxid() + "," + stat2.getMzxid() + "," + stat2.getVersion());

            // 错误的 version，无法更新:KeeperErrorCode = BadVersion
            Stat stat3 = zk.setData(path, "456".getBytes(), stat.getVersion());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            }
        }
    }
}
