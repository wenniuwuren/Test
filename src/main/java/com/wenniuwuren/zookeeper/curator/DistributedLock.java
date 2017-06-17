package com.wenniuwuren.zookeeper.curator;

import com.wenniuwuren.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁
 * Created by Yibin_Zhu on 2017/6/18.
 */
public class DistributedLock {

    static String lock_path = "/curator_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString(Constants.ZK_HOST)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) {
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, lock_path);
        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 30; i++) {
            new Thread(() ->
                {
                    try {
                        down.await();
                        lock.acquire();
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("订单号：" + orderNo);

                    try {
                        lock.release();
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }

                }
            ).start();
        }
        down.countDown();
    }
}
