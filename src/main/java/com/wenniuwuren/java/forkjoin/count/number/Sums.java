package com.wenniuwuren.java.forkjoin.count.number;

import java.util.*;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

/**
 * 与 CountForkJoin 对比
 * 此类使用普通的 Executors 实现累加
 * 使用执行器解决以上问题很简单：将数组分为 n 个可用物理处理单元，
 * 创建 Callable 实例以计算每个部分和，将部分和提交给管理 n 个线程的线程池的执行器，然后收集结果以计算最终和。
 */
public class Sums {

    static class Sum implements Callable<Long> {
        private final long from;
        private final long to;

        Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() {
            long acc = 0;
            for (long i = from; i <= to; i++) {
                acc = acc + i;
            }
            return acc;
        }
    }

    public static void main(String[] args) throws Exception {
        int count = 0;

        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 分成 2 个数据块
        List<Future<Long>> results = executor.invokeAll(asList(new Sum(1, 3), new Sum(4, 5)));
        executor.shutdown();

        // 统计最后结果
        for (Future<Long> result : results) {
            count += result.get();
        }

        System.out.println(count);
    }
}