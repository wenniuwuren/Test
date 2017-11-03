package com.wenniuwuren.zookeeper.curator;


import java.io.IOException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.wenniuwuren.Constants;

/**
 * 使用zk只会成功创建同一一个节点的特性来选出唯一一个 leader
 * @author wenniuwuren
 */
public class MasterSelectUseLeaderLatch {

    private CuratorFramework client;
    private String latchPath;
    private String id;
    private LeaderLatch leaderLatch;

    public MasterSelectUseLeaderLatch(String connString, String latchPath, String id) {
        client = CuratorFrameworkFactory.newClient(connString, new ExponentialBackoffRetry(1000, Integer.MAX_VALUE));
        this.id = id;
        this.latchPath = latchPath;
    }

    public void start() throws Exception {
        client.start();
        client.getZookeeperClient().blockUntilConnectedOrTimedOut();
        leaderLatch = new LeaderLatch(client, latchPath, id);
        leaderLatch.start();
    }

    public boolean isLeader() {
        return leaderLatch.hasLeadership();
    }

    public Participant currentLeader() throws Exception {
        return leaderLatch.getLeader();
    }

    public void close() throws IOException {
        leaderLatch.close();
        client.close();
    }

    /**
     * 当前线程等待直到这个instance成为leader，除非线程中断或者close
     * @throws Exception
     */
    public void await() throws Exception{
        leaderLatch.await();
    }


    public static void main(String[] args) throws Exception {
        String latchPath = "/latch";
        String connStr = Constants.ZK_HOST;
        MasterSelectUseLeaderLatch node1 = new MasterSelectUseLeaderLatch(connStr, latchPath, "node-1");
        MasterSelectUseLeaderLatch node2 = new MasterSelectUseLeaderLatch(connStr, latchPath, "node-2");

        node1.start();
        node1.await();

        node2.start();

        for (int i = 0; i < 3; i++) {
            System.out.println("node-1 think the leader is " + node1.currentLeader());
            System.out.println("node-2 think the leader is " + node2.currentLeader());
            Thread.sleep(5000);
        }

        node1.close(); // 关闭来切换leader

        Thread.sleep(5000); // 预留选举时间，不然很可能还是旧的leader
        System.out.println("now node-2 think the leader is " + node2.currentLeader());

        node2.close();

    }
}