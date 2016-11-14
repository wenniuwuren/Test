package com.wenniuwuren.nio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzzhuyibin on 2016/8/6.
 */
public class TimeServerHandlerExecutePool {
    private ExecutorService executorService;

    public TimeServerHandlerExecutePool (int maxPoolSize, int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));

    }

    public void execute(Runnable task) {
        executorService.execute(task);
    }

}

