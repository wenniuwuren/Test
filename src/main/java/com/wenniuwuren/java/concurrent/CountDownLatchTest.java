
package com.wenniuwuren.java.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuyibin
 */
public class CountDownLatchTest {

    static int shardNum = 3;
    static CountDownLatch countDownLatch = new CountDownLatch(shardNum);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 1000L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(200), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {

        try {
            for (int i = 0; i < shardNum; i++) {
                threadPoolExecutor.execute(new RunTask());
            }
            countDownLatch.await();

            System.out.println("main thread execute other things");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }


    }

    static class RunTask implements Runnable {

        @Override
        public void run() {
            System.out.println("dealing task ...");
            try {
                Thread.sleep(2000L);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}