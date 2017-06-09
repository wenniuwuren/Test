package com.wenniuwuren.java.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * 使用阿里规范定义线程池
 * 阿里规范不直接使用这个(ExecutorService executorService = Executors.newFixedThreadPool(10);)，
 * 因为这个隐藏了线程池的内部细节，为了让程序员更理解线程池
 *
 * Created by hzzhuyibin on 2017/5/2.
 */
public class ThreadPoolTest {
    // （线程池的基本大小）：当提交一个任务到线程池时，线程池会创建一个线程来执行任务，
    // 即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。
    // 如果调用了线程池的prestartAllCoreThreads方法，线程池会提前创建并启动所有基本线程。
    static final int corePoolSize = 10;

    // 线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。
    // 值得注意的是如果使用了无界的任务队列这个参数就没什么效果。
    static final int maxPoolSize = 10;

    // 线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率。
    static final long keepAliveTime = 1000L;

    // 用于保存等待执行的任务的阻塞队列。 可以选择以下几个阻塞队列。
    // ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，FIFO
    // LinkedBlockingQueue：一个基于链表结构的无界阻塞队列，FIFO ，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
    // SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
    // PriorityBlockingQueue：一个具有优先级的无界阻塞队列。
    static BlockingQueue blockingDeque = new LinkedBlockingQueue();

    // 当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。以下是JDK1.5提供的四种策略。
    // AbortPolicy：拒绝接受任务，并抛出异常。
    // CallerRunsPolicy：用调用者所在线程来运行任务, executor shutdown 了任务才会被抛弃
    // DiscardOldestPolicy：丢弃队列里最老的一个任务，并执行当前任务。
    // DiscardPolicy：丢弃掉。
    // 当然也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化不能处理的任务。
    static RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
            TimeUnit.MILLISECONDS, blockingDeque, rejectedExecutionHandler);


    public static void main(String[] args) {
        threadPoolExecutor.execute(() -> System.out.println("aa"));
    }
}

