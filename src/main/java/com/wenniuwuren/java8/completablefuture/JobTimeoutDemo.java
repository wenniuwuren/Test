package com.wenniuwuren.java8.completablefuture;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/**
 * Future 接口局限性：Future 之间的依赖性
 * Created by hzzhuyibin on 2017/5/19.
 */
public class JobTimeoutDemo {

    public static void main(String[] args) {
        System.out.println("Start...");

        ExecutorService exec = Executors.newFixedThreadPool(1);

        testTask(exec, 11); // 在11秒内任务成功结束后计算结果，超过11秒终止
        testTask(exec, 5); //  等待5秒，任务还没结束，所以将任务中止

        exec.shutdown();
        System.out.println("End...");
    }

    public static void testTask(ExecutorService exec, int timeout) {

        Future<Boolean> future = null;
        Boolean isTimeout = null;
        String failReason = "任务执行成功";

        // 异步执行任务
        future = exec.submit(new Callable<Boolean>() { // 执行任务
            public Boolean call() throws Exception {
                // 总计耗时约10秒
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100);
                    System.out.print('-');
                }
                return Boolean.TRUE;
            }
        });

        // 这里线程空闲出来可以做其他事情
        System.out.println("这里线程空闲出来可以做其他事情");

        try {
            // 等待计算结果，最长等待timeout秒，timeout秒后终止任务
            isTimeout = future.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            failReason = "when a thread is waiting, sleeping, or otherwise occupied, " +
                    "and the thread is interrupted, either before or during the activity";
        } catch (ExecutionException e) {
            failReason = " attempting to retrieve the result of a task that aborted";
        } catch (TimeoutException e) {
            failReason = "a blocking operation times out";
            // 会等待之前提交的任务完成，关闭线程
            exec.shutdown();  // 测试先放这里， 实际环境一般都是在finally里面执行
        } finally {
        }

        System.out.println("\nisTimeout : " + isTimeout);
        System.out.println("reason : " + failReason);
    }
}

