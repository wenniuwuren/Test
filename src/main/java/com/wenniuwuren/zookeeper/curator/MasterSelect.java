package com.wenniuwuren.zookeeper.curator;

import com.wenniuwuren.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Master选举 大致思路：选择一个根节点，例如 /master_select，多台机器同时向该节点创建一个子节点/master_select/lock，
 * 利用 zk 的特性，最终只有一台机器能成功
 * Created by Yibin_Zhu on 2017/6/17.
 */
public class MasterSelect {

    static String master_path = "/curator_master_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString(Constants.ZK_HOST)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) {

        try {
            client.start();
            LeaderSelector selector = new LeaderSelector(client, master_path,
                    new LeaderSelectorListenerAdapter() { // Curator在成功获取Master权限时回调此监听器，在此实现业务
                        @Override
                        public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                            System.out.println("become Master");
                            Thread.sleep(3000);
                            System.out.println("finish Master operate, release Master lock");
                        }
                    });

            selector.autoRequeue();
            selector.start();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
