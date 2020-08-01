/**
 * @(#)RateLimitTest.java, 2020/7/16.
 * author wenniuwuren
 */
package com.wenniuwuren.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import java.util.List;
import java.util.concurrent.Executor;

public class RateLimitTest {

    final RateLimiter rateLimiter = RateLimiter.create(2.0);


    public static void main(String[] args) {





    }

    void submitTasks(List<Runnable> tasks, Executor executor) {
        for (Runnable task : tasks) {
            rateLimiter.acquire(); // may wait
            executor.execute(task);
        }
    }
}