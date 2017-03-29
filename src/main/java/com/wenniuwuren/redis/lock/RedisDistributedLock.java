package com.wenniuwuren.redis.lock;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;


/**
 * Created by hzzhuyibin on 2017/3/22.
 */
public class RedisDistributedLock {

    private static final String redisHost = "192.168.227.128"; // 公司:192.168.227.128 自己电脑:

    private static final int port = 6379; // 公司:6379

    private static JedisPoolConfig config;

    private static JedisPool pool;

    private static ExecutorService service;

    private static final int THREAD_COUNT = 10;

    private static CountDownLatch latch;

    private static AtomicInteger counter = new AtomicInteger(0);

    private static int count = 0;

    private static final String LOCK_NAME = "lockName";

    private static final String PASSWORD = "redis";

    private static final int TIME_OUT = 1000;

    static {
        config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxWaitMillis(1000);
        config.setMaxTotal(30);
        pool = new JedisPool(config, redisHost, port, TIME_OUT, PASSWORD);

        service = Executors.newFixedThreadPool(THREAD_COUNT);
        //CountDownLatch保证主线程在全部线程结束之后退出
        latch = new CountDownLatch(THREAD_COUNT);
    }

    /**
     * 获取锁
     * 生成一个UUID，作为 Key 的标识，不断轮询 lockName，直到 set 成功，表示成功获取锁。
     * 其他的线程在 set 此 lockName 时被阻塞直到超时。
     *
     * @param pool
     * @param lockName
     * @param timeouts
     * @return 锁标识
     */
    public static String getLock(String threadName, JedisPool pool, String lockName, long timeouts) {
        Jedis jedis = pool.getResource();
        int sleepTime = 1000;
        try {
            String value = UUID.randomUUID().toString();
            long timeWait = System.currentTimeMillis() + timeouts * 1000;

            while (System.currentTimeMillis() < timeWait) {
                if (jedis.setnx(lockName, value) == 1) { // 这里用到了 redis 的 setnx，不存在则赋值，返回 1
                    System.out.println("lock geted");
                    return value;
                }

                // 获取不到锁，sleep 1s
                System.out.println("threadName=" + threadName + " sleep=" + sleepTime);
                Thread.currentThread().sleep(sleepTime);

            }
            // 获取不到锁，超时就放弃这次获取锁了
            System.out.println("threadName=" + threadName + " get lock timeout");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 释放锁
     * 对 lockName watch，开启一个事务，删除以 LockName 为 key 的锁，删除后，此锁对于其他线程为可争抢的。
     *
     * @param pool
     * @param lockName
     * @param value
     */
    public static void relaseLock(String threadName, JedisPool pool, String lockName, String value) {
        Jedis jedis = pool.getResource();
        try {
            while (true) {
                jedis.watch(lockName);
                System.out.println("threadName=" + threadName + ", jedis.get(lockName) = " + jedis.get(lockName));
                if (jedis.get(lockName).equals(value)) {
                    Transaction tx = jedis.multi();
                    tx.del(lockName);
                    tx.exec();
                    return;
                }
                jedis.unwatch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }


    public static void main(String args[]) {
        try {

            for (int i = 0; i < THREAD_COUNT; i++) {
                final String threadName = "thread-" + i;

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(threadName + " starting...");
                            String value = getLock(threadName, pool, LOCK_NAME, 50); // 50s 超时
                            System.out.println(threadName + " get Lock " + value);

                            // 过 10s 释放锁，观察结果比较方便。
                            // 从结果可以看出来，因为前 5 个 sleep 了 10s 所以后面 5 个最终都没拿到锁
                            Thread.sleep(10000);

                            if (StringUtils.isNotBlank(value)) { // 获得锁才去释放
                                relaseLock(threadName, pool, LOCK_NAME, value);
                                count++;
                                counter.incrementAndGet();
                            }

                            System.out.println(threadName + " " + count);

                            latch.countDown();
                            System.out.println(threadName + " complated");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

                System.out.println(threadName + "inited...");
                service.submit(t);
            }

            latch.await();

            service.shutdown();
            System.out.println(counter.get());
            System.out.println(count); // 从 count 与 counter 结果比较来看，可以发现没有并发问题，说明 redis 锁起作用了
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}

