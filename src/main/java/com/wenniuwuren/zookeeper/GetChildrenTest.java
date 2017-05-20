package com.wenniuwuren.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 获取目录下的所有子节点及节点变化通知
 * Created by Yibin_Zhu on 2017/4/15.
 */
public class GetChildrenTest implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    public static final String ip = "192.168.116.128:2181";

    public static void main(String[] args) {
        try {
            // 运行一次改变一次 path，因为每次都是 PERSISTENT 创建已经存在了
            String path = "/zk-boooooook";
            zk = new ZooKeeper(ip, 5000, new GetChildrenTest());

            countDownLatch.await();

            // 创建父节点
            zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            // 创建子节点
            zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

            // [同步]获取子节点
//            List<String> childrenList = zk.getChildren(path, true);
//            System.out.println(childrenList);

            // [异步]获取子节点
            zk.getChildren(path, true, new ChildrenCallBack(), null);

            // 增加子节点，观察 watcher 通知节点变化
            zk.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void process(WatchedEvent watchedEvent) {
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    countDownLatch.countDown();
                } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                    try {
                        System.out.println("get new child list:" + zk.getChildren(watchedEvent.getPath(), true));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

    }

    static class ChildrenCallBack implements AsyncCallback.Children2Callback {

        @Override
        public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
            System.out.println("get children znode: [response code:" + i + ", param path:" + s + ", ctx:" + o
                    + ", children list:" + list +  ", stat:" + stat);
        }
    }
}
